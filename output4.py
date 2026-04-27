import numpy as np
import pandas as pd
import torch
import snntorch as snn


class Neuron:
    def __init__(self, beta=0.9):
        self.neuron = snn.Leaky(beta=0.9)
        self.connections = []
        self.weights = []
        self.history = []
        self.input_spikes = torch.tensor(0.0)
        self.spike = torch.tensor(0.0)
        self.mem = torch.tensor(0.0)

    def add_connection(self, n, weight=0.7, randomize_weight=True):
        self.connections.append(n)
        self.weights.append(np.random.random() if randomize_weight else weight)

    def add_connections(self, n_arr: list):
        for n in n_arr:
            self.add_connection(n)

    def receive_spike(self, input):
        self.input_spikes += torch.tensor(input)

    def send_spike(self):
        for i, c in enumerate(self.connections):
            c.receive_spike(self.spike * min(self.weights[i], 0.3))

    def time_step(self):
        self.spike, self.mem = self.neuron(self.input_spikes, self.mem)
        self.history.append({
            "input": self.input_spikes,
            "spike": self.spike,
            "mem": self.mem,
        })
        self.input_spikes = torch.tensor(0.0)


class LSM:
    class Class_Neuron(Neuron):
        def __init__(self, beta=0.9):
            super().__init__(beta)

    def __init__(self, size, threshold=1.0, beta=0.9):
        self.size = size
        self.threshold = threshold
        self.mem_potential = 0.0
        self.spike_state = torch.tensor(0.0)
        self.neurons = self.init_neurons(self.size)
        self.connected_layers = []
        self.make_connections(self.neurons, num_connections=2)

    def init_neurons(self, num: int) -> list:
        neurons = []
        for _ in range(num):
            neurons.append(self.Class_Neuron())
        return neurons

    def make_connections(self, neurons: list, num_connections=1) -> list:
        for neuron in neurons:
            if len(neurons) == 0:
                continue
            count = min(num_connections, len(neurons))
            if count <= 0:
                continue
            choices = np.random.choice(len(neurons), size=count, replace=False)
            neuron.add_connections([neurons[i] for i in np.atleast_1d(choices)])

    def time_step(self, input_value=None, time_steps=1):
        for _ in range(time_steps):
            for neuron in self.neurons:
                if input_value is not None:
                    neuron.receive_spike(input_value)
                neuron.time_step()
                neuron.send_spike()
        if self.neurons:
            self.mem_potential = self.neurons[0].mem
            self.spike_state = self.neurons[0].spike

    def spike(self, input_value=None, time_steps=1):
        return self.time_step(input_value=input_value, time_steps=time_steps)

    def readout(self) -> list:
        return [n.mem for n in self.neurons]

    def add_layer(self, layer, weight):
        self.connected_layers.append((layer, weight))


class Layer:
    def __init__(self, name, size=None, layer_type=None, beta=0.9):
        self.name = name
        self.size = size
        self.layer_type = layer_type
        self.beta = beta
        self.connected_layers = []
        self.readout_history = []

    def forward(self, x):
        return x

    def readout(self):
        return self.forward(None)

    def add_layer(self, layer, weight):
        self.connected_layers.append((layer, weight))


class Answer(Layer):
    def __init__(self, name, size=None, layer_type=None, beta=0.9):
        super().__init__(name=name, size=size, layer_type=layer_type, beta=beta)
        self.outputs = []
        self.mem_potential = 0.0

    def forward(self, x):
        self.outputs.append(x)
        return x

    def readout(self):
        return self.forward(None)

class RDLDatasetConfig:
    def __init__(self, mode="preencoded", sim_steps=1, encode_steps=1, batch_size=1):
        self.mode = mode
        self.sim_steps = sim_steps
        self.encode_steps = max(1, int(encode_steps))
        self.batch_size = max(1, int(batch_size))

train_data = pd.read_csv("data.csv")

class reservoir_a(LSM):
    def __init__(self, size=128, threshold=1.0, beta=0.9):
        super().__init__(size=size, threshold=threshold, beta=beta)
        self.name = "reservoir_a"
        self.threshold_local = 0.95

    def receive_spike(self, spike_amount):
        parent = getattr(super(), "receive_spike", None)
        if callable(parent):
            parent(spike_amount)
        self.mem_potential += spike_amount

    def spike(self):
        parent = getattr(super(), "spike", None)
        if callable(parent):
            parent()
        if self.mem_potential>self.threshold:
            parent = getattr(super(), "send_spike", None)
            if callable(parent):
                parent()
            self.mem_potential = 0

    def spike_rule(self):
        return self.spike()

class proj(Layer):
    def __init__(self):
        super().__init__(name="proj", size=64, layer_type="dense projection")

    def forward(self, x):
        parent = getattr(super(), "forward", None)
        if callable(parent):
            parent(x)
        self.spike_state = x
        return getattr(self, "spike", None)

class classifier(Answer):
    def __init__(self):
        super().__init__(name="classifier", size=None, layer_type="linear readout")

    def readout(self):
        parent = getattr(super(), "readout", None)
        if callable(parent):
            parent()
        self.spike_state = self.mem_potential
        return getattr(self, "spike", None)

reservoir_a_instance = reservoir_a()
proj_instance = proj()
classifier_instance = classifier()

NODE_INSTANCES = {
    "reservoir_a": reservoir_a_instance,
    "proj": proj_instance,
    "classifier": classifier_instance,
}

GRAPH_CONNECTIONS = [
    ("reservoir_a", "proj", 1.0),
    ("proj", "classifier", 0.6),
]

READOUT_NODES = [
    "classifier",
]

def _safe_to_numpy(sample):
    if hasattr(sample, "to_numpy"):
        arr = sample.to_numpy(dtype=np.float32)
    else:
        arr = np.asarray(sample, dtype=np.float32)
    return np.nan_to_num(arr.astype(np.float32))

def _encode_sample(sample, cfg):
    values = _safe_to_numpy(sample)

    if cfg.mode == "preencoded":
        return [values for _ in range(cfg.sim_steps)]

    min_v = float(np.min(values))
    max_v = float(np.max(values))
    denom = max(max_v - min_v, 1e-8)
    normalized = (values - min_v) / denom

    if cfg.mode == "rate":
        seq = []
        for _ in range(cfg.encode_steps):
            spikes = (np.random.rand(*normalized.shape) < normalized).astype(np.float32)
            seq.append(spikes)
        return seq

    if cfg.mode == "latency":
        seq = [np.zeros_like(normalized, dtype=np.float32) for _ in range(cfg.encode_steps)]
        latencies = ((1.0 - normalized) * (cfg.encode_steps - 1)).astype(int)
        for idx, latency in np.ndenumerate(latencies):
            seq[int(latency)][idx] = 1.0
        return seq

    raise ValueError(f"Unsupported encoding mode: {cfg.mode}")

def _to_scalar(value):
    if isinstance(value, torch.Tensor):
        if value.numel() == 0:
            return 0.0
        return float(value.detach().float().mean().item())
    arr = np.asarray(value, dtype=np.float32)
    if arr.size == 0:
        return 0.0
    return float(np.mean(arr))

def _node_step(node, input_value):
    if hasattr(node, "receive_spike") and callable(getattr(node, "receive_spike")):
        node.receive_spike(_to_scalar(input_value))
    if hasattr(node, "spike_rule") and callable(getattr(node, "spike_rule")):
        node.spike_rule()
    elif hasattr(node, "time_step") and callable(getattr(node, "time_step")):
        node.time_step()

    if hasattr(node, "readout") and callable(getattr(node, "readout")):
        maybe = node.readout()
        if maybe is not None:
            return maybe

    if hasattr(node, "forward") and callable(getattr(node, "forward")):
        return node.forward(input_value)

    return input_value

def _entry_nodes(connections, node_map):
    in_degree = {name: 0 for name in node_map.keys()}
    for _src, dst, _w in connections:
        if dst in in_degree:
            in_degree[dst] += 1
    roots = [name for name, degree in in_degree.items() if degree == 0]
    return roots if roots else list(node_map.keys())

def _run_single_sample(encoded_sequence, node_map, connections, explicit_entry=None):
    entries = [explicit_entry] if explicit_entry else _entry_nodes(connections, node_map)
    sample_outputs = []

    for tick in encoded_sequence:
        activations = {name: tick for name in entries if name in node_map}
        for node_name in list(node_map.keys()):
            if node_name not in activations:
                continue
            node_out = _node_step(node_map[node_name], activations[node_name])
            for src, dst, weight in connections:
                if src != node_name:
                    continue
                weighted = _to_scalar(node_out) * float(weight)
                if dst in activations:
                    activations[dst] = _to_scalar(activations[dst]) + weighted
                else:
                    activations[dst] = weighted

        if READOUT_NODES:
            tick_out = {name: _node_step(node_map[name], activations.get(name, 0.0)) for name in READOUT_NODES if name in node_map}
        else:
            sinks = [dst for _src, dst, _w in connections]
            tick_out = {name: activations[name] for name in activations if name not in sinks}
        sample_outputs.append(tick_out)

    return sample_outputs

simulation_outputs = []
simulation_config = RDLDatasetConfig(mode="latency", sim_steps=20, encode_steps=20, batch_size=4)
simulation_data = train_data
simulation_rows = simulation_data.head(simulation_config.batch_size)
for _, row in simulation_rows.iterrows():
    encoded = _encode_sample(row, simulation_config)
    sample_out = _run_single_sample(encoded, NODE_INSTANCES, GRAPH_CONNECTIONS, explicit_entry="reservoir_a")
    simulation_outputs.append(sample_out)

torch.save({
    "target": "classifier",
    "outputs": simulation_outputs,
    "connections": GRAPH_CONNECTIONS,
    "encoding": simulation_config.mode,
}, "classifier.pt")
