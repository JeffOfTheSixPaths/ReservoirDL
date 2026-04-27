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

    def forward(self, x):
        self.outputs.append(x)
        return x

    def readout(self):
        return self.forward(None)


if __name__ == "__main__":
    #simulation
    df = 0  # "dataframe"
    time_steps = 100  # this is defined from the previous language
    for _ in range(time_steps):
        sample = 1  # sample from df
        # simulate everything here and print results
        pass
