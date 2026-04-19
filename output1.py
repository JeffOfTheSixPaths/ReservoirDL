import torch
import snntorch as snn
import numpy as np
import pandas as pd

class Neuron:
    def __init__(self, beta=0.9):
        self.neuron = snn.Leaky(beta=beta)
        self.connections = []
        self.weights = []
        self.history = []
        self.input_spikes = torch.tensor(0.0)
        self.spike = torch.tensor(0.0)
        self.mem = torch.tensor(0.0)

    def add_connection(self, n, weight=0.7, randomize_weight=True):
        self.connections.append(n)
        self.weights.append(np.random.random() if randomize_weight else weight)

    def add_connections(self, n_arr):
        for n in n_arr:
            self.add_connection(n)

    def receive_spike(self, value):
        self.input_spikes += torch.tensor(value)

    def send_spike(self):
        for i, c in enumerate(self.connections):
            c.receive_spike(self.spike * min(self.weights[i], 0.3))

    def time_step(self):
        self.spike, self.mem = self.neuron(self.input_spikes, self.mem)
        self.history.append({
            "input": self.input_spikes,
            "spike": self.spike,
            "mem": self.mem
        })
        self.input_spikes = torch.tensor(0.0)

class LSM(Neuron):
    def __init__(self, threshold=1.0, beta=0.9):
        super().__init__(beta=beta)
        self.threshold = threshold

class Layer:
    def __init__(self, name, size=None, layer_type=None):
        self.name = name
        self.size = size
        self.layer_type = layer_type
        self.outgoing = []

train_data = pd.read_csv("data.csv")

class l(LSM):
    def __init__(self, threshold=1.0, beta=0.9):
        super().__init__(threshold=threshold, beta=beta)

    def spike(self):
        if self.mem>self.threshold:
            self.send_spike()
            self.mem = 0

class l1(Layer):
    def __init__(self):
        super().__init__(name="l1", size=5, layer_type=None)

class l2(Layer):
    def __init__(self):
        super().__init__(name="l2", size=None, layer_type="lsm Reservoir")

class answers(Layer):
    def __init__(self):
        super().__init__(name="answers", size=None, layer_type="regression")

class custom_layer(Layer):
    def __init__(self):
        super().__init__(name="custom_layer", size=None, layer_type="custom")

l_instance = l()
l1_instance = l1()
l2_instance = l2()
answers_instance = answers()
custom_layer_instance = custom_layer()

connections = []
connections.append(("l1", "l2", 0.5))
connections.append(("l2", "answers", 1.0))

# Simulation
simulation_data = None
simulation_data = train_data
for _ in range(100):
    l_instance.spike_rule()

# Save target artifact
torch.save({"state": "l"}, "l.pt")
