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

class LSM:
    def __init__(self, size=1, threshold=1.0, beta=0.9):
        self.size = size
        self.threshold = threshold
        self.neurons = [Neuron(beta=beta) for _ in range(size)]
        self.recurrent_connections = []
        self.connect_reservoir()

    def connect_reservoir(self):
        for source_index, source in enumerate(self.neurons):
            for target_index, target in enumerate(self.neurons):
                if source_index != target_index:
                    source.add_connection(target, randomize_weight=True)
                    self.recurrent_connections.append((source_index, target_index))

    def receive_spike(self, value):
        if self.neurons:
            self.neurons[0].receive_spike(value)

    def send_spike(self):
        for neuron in self.neurons:
            neuron.send_spike()

    def time_step(self):
        for neuron in self.neurons:
            neuron.time_step()
        for neuron in self.neurons:
            neuron.send_spike()

class Layer:
    def __init__(self, name, size=None, layer_type=None):
        self.name = name
        self.size = size
        self.layer_type = layer_type
        self.outgoing = []

class l(LSM):
    def __init__(self, threshold=1.0, beta=0.9):
        super().__init__(size=10, threshold=threshold, beta=beta)

    def recieve(self, spike_amount):
        for neuron in self.neurons:
            neuron.mem += spike_amount

    def spike(self):
        for neuron in self.neurons:
            if neuron.mem>self.threshold:
                neuron.send_spike()
                neuron.mem = 0

    def spike_rule(self):
        return self.spike()

    def emit_spike(self, weight):
        for neuron in self.neurons:
            weight += 1
            this_is_a_counter_variable += 1

l_instance = l()
# Simulation
simulation_data = None
for _ in range(1):
    l_instance.spike_rule()
