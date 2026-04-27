import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RDLToPythonVisitor extends RDLBaseVisitor<String> {

    private static final class DataSource {
        String name;
        String csvPath;
    }

    private static final class LsmSpec {
        String name;
        int size = 1;
        List<RDLParser.BlockStatementContext> methodStatements = new ArrayList<>();
        List<RDLParser.AssignmentContext> initAssignments = new ArrayList<>();
        Set<String> knownFields = new LinkedHashSet<>();
    }

    private static final class LayerSpec {
        String name;
        boolean torchLayer;
        boolean readout;
        Integer size;
        String type;
        List<RDLParser.BlockStatementContext> methodStatements = new ArrayList<>();
        List<RDLParser.AssignmentContext> initAssignments = new ArrayList<>();
        Set<String> knownFields = new LinkedHashSet<>();
    }

    private static final class ConnectionSpec {
        String from;
        String to;
        String weight;
    }

    private static final class SimulationSpec {
        String dataName;
        String entryName;
        int steps = 1;
        String encoding = "preencoded";
        int encodeSteps = 1;
        int batchSize = 1;
    }

    private final Map<String, DataSource> dataSources = new LinkedHashMap<>();
    private final Map<String, LsmSpec> lsmSpecs = new LinkedHashMap<>();
    private final Map<String, LayerSpec> layerSpecs = new LinkedHashMap<>();
    private final List<ConnectionSpec> connections = new ArrayList<>();

    private SimulationSpec simulationSpec;
    private String saveTarget;

    @Override
    public String visitProgram(RDLParser.ProgramContext ctx) {
        for (RDLParser.StatementContext statement : ctx.statement()) {
            visit(statement);
        }
        return buildPython();
    }

    @Override
    public String visitDataDecl(RDLParser.DataDeclContext ctx) {
        DataSource ds = new DataSource();
        ds.name = ctx.ID().getText();
        ds.csvPath = unquote(ctx.STRING().getText());
        dataSources.put(ds.name, ds);
        return null;
    }

    @Override
    public String visitLsmDecl(RDLParser.LsmDeclContext ctx) {
        LsmSpec spec = new LsmSpec();
        spec.name = ctx.ID().getText();

        for (RDLParser.BlockStatementContext bs : ctx.block().blockStatement()) {
            if (bs.sizeStmt() != null) {
                spec.size = Integer.parseInt(bs.sizeStmt().INT().getText());
            } else if (bs.assignment() != null) {
                spec.initAssignments.add(bs.assignment());
                spec.knownFields.add(bs.assignment().name().getText());
            } else if (bs.methodDecl() != null || bs.spikeDecl() != null) {
                spec.methodStatements.add(bs);
            }
        }

        spec.knownFields.add("threshold");
        spec.knownFields.add("mem_potential");
        spec.knownFields.add("spike");

        lsmSpecs.put(spec.name, spec);
        return null;
    }

    @Override
    public String visitLayerDecl(RDLParser.LayerDeclContext ctx) {
        LayerSpec spec = new LayerSpec();
        spec.name = ctx.ID().getText();
        spec.torchLayer = ctx.TORCH() != null;
        spec.readout = false;
        populateLayerSpec(spec, ctx.block().blockStatement());
        layerSpecs.put(spec.name, spec);
        return null;
    }

    @Override
    public String visitReadoutDecl(RDLParser.ReadoutDeclContext ctx) {
        LayerSpec spec = new LayerSpec();
        spec.name = ctx.ID().getText();
        spec.torchLayer = ctx.TORCH() != null;
        spec.readout = true;
        populateLayerSpec(spec, ctx.block().blockStatement());
        layerSpecs.put(spec.name, spec);
        return null;
    }

    @Override
    public String visitConnectStmt(RDLParser.ConnectStmtContext ctx) {
        ConnectionSpec c = new ConnectionSpec();
        c.from = ctx.ID(0).getText();
        c.to = ctx.ID(1).getText();
        c.weight = ctx.number() != null ? ctx.number().getText() : "1.0";
        connections.add(c);
        return null;
    }

    @Override
    public String visitSimulateStmt(RDLParser.SimulateStmtContext ctx) {
        SimulationSpec spec = new SimulationSpec();
        spec.dataName = ctx.ID(0).getText();
        if (ctx.THROUGH() != null) {
            spec.entryName = ctx.ID(1).getText();
        }

        for (RDLParser.BlockStatementContext bs : ctx.block().blockStatement()) {
            if (bs.stepsStmt() != null) {
                spec.steps = Integer.parseInt(bs.stepsStmt().INT().getText());
            } else if (bs.encodingStmt() != null) {
                spec.encoding = bs.encodingStmt().encodingType().getText().toLowerCase();
            } else if (bs.encodeStepsStmt() != null) {
                spec.encodeSteps = Integer.parseInt(bs.encodeStepsStmt().INT().getText());
            } else if (bs.batchStmt() != null) {
                spec.batchSize = Integer.parseInt(bs.batchStmt().INT().getText());
            }
        }

        simulationSpec = spec;
        return null;
    }

    @Override
    public String visitSaveStmt(RDLParser.SaveStmtContext ctx) {
        saveTarget = ctx.ID().getText();
        return null;
    }

    private void populateLayerSpec(LayerSpec spec, List<RDLParser.BlockStatementContext> blockStatements) {
        for (RDLParser.BlockStatementContext bs : blockStatements) {
            if (bs.sizeStmt() != null) {
                spec.size = Integer.parseInt(bs.sizeStmt().INT().getText());
            } else if (bs.typeStmt() != null) {
                spec.type = joinTypeValue(bs.typeStmt().typeValue());
            } else if (bs.assignment() != null) {
                spec.initAssignments.add(bs.assignment());
                spec.knownFields.add(bs.assignment().name().getText());
            } else if (bs.methodDecl() != null || bs.spikeDecl() != null) {
                spec.methodStatements.add(bs);
            }
        }

        spec.knownFields.add("spike");
        spec.knownFields.add("mem_potential");
    }

    private String buildPython() {
        StringBuilder out = new StringBuilder();
        //used a python script to just output "out.append(...)" for a lot of these lines 
        out.append(loadPreludeText());
        out.append("\n\n");

        out.append("class RDLDatasetConfig:\n");
        out.append("    def __init__(self, mode=\"preencoded\", sim_steps=1, encode_steps=1, batch_size=1):\n");
        out.append("        self.mode = mode\n");
        out.append("        self.sim_steps = sim_steps\n");
        out.append("        self.encode_steps = max(1, int(encode_steps))\n");
        out.append("        self.batch_size = max(1, int(batch_size))\n\n");

        for (DataSource ds : dataSources.values()) {
            out.append(ds.name)
                .append(" = pd.read_csv(\"")
                .append(escapePython(ds.csvPath))
                .append("\")\n");
        }
        if (!dataSources.isEmpty()) {
            out.append("\n");
        }

        for (LsmSpec lsm : lsmSpecs.values()) {
            emitLsmClass(out, lsm);
        }

        for (LayerSpec layer : layerSpecs.values()) {
            emitLayerClass(out, layer);
        }

        if (!lsmSpecs.isEmpty()) {
            for (LsmSpec lsm : lsmSpecs.values()) {
                out.append(lsm.name).append("_instance = ").append(lsm.name).append("()\n");
            }
        }
        if (!layerSpecs.isEmpty()) {
            for (LayerSpec layer : layerSpecs.values()) {
                out.append(layer.name).append("_instance = ").append(layer.name).append("()\n");
            }
        }

        if (!lsmSpecs.isEmpty() || !layerSpecs.isEmpty()) {
            out.append("\nNODE_INSTANCES = {\n");
            for (LsmSpec lsm : lsmSpecs.values()) {
                out.append("    \"")
                    .append(lsm.name)
                    .append("\": ")
                    .append(lsm.name)
                    .append("_instance,\n");
            }
            for (LayerSpec layer : layerSpecs.values()) {
                out.append("    \"")
                    .append(layer.name)
                    .append("\": ")
                    .append(layer.name)
                    .append("_instance,\n");
            }
            out.append("}\n\n");
        }

        out.append("GRAPH_CONNECTIONS = [\n");
        for (ConnectionSpec c : connections) {
            out.append("    (\"")
                .append(c.from)
                .append("\", \"")
                .append(c.to)
                .append("\", ")
                .append(c.weight)
                .append("),\n");
        }
        out.append("]\n\n");

        out.append("READOUT_NODES = [\n");
        for (LayerSpec layer : layerSpecs.values()) {
            if (layer.readout) {
                out.append("    \"").append(layer.name).append("\",\n");
            }
        }
        out.append("]\n\n");
        // paste methods
        out.append("def _safe_to_numpy(sample):\n");
        out.append("    if hasattr(sample, \"to_numpy\"):\n");
        out.append("        arr = sample.to_numpy(dtype=np.float32)\n");
        out.append("    else:\n");
        out.append("        arr = np.asarray(sample, dtype=np.float32)\n");
        out.append("    return np.nan_to_num(arr.astype(np.float32))\n\n");

        out.append("def _encode_sample(sample, cfg):\n");
        out.append("    values = _safe_to_numpy(sample)\n");
        out.append("\n");
        out.append("    if cfg.mode == \"preencoded\":\n");
        out.append("        return [values for _ in range(cfg.sim_steps)]\n");
        out.append("\n");
        out.append("    min_v = float(np.min(values))\n");
        out.append("    max_v = float(np.max(values))\n");
        out.append("    denom = max(max_v - min_v, 1e-8)\n");
        out.append("    normalized = (values - min_v) / denom\n");
        out.append("\n");
        out.append("    if cfg.mode == \"rate\":\n");
        out.append("        seq = []\n");
        out.append("        for _ in range(cfg.encode_steps):\n");
        out.append("            spikes = (np.random.rand(*normalized.shape) < normalized).astype(np.float32)\n");
        out.append("            seq.append(spikes)\n");
        out.append("        return seq\n");
        out.append("\n");
        out.append("    if cfg.mode == \"latency\":\n");
        out.append("        seq = [np.zeros_like(normalized, dtype=np.float32) for _ in range(cfg.encode_steps)]\n");
        out.append("        latencies = ((1.0 - normalized) * (cfg.encode_steps - 1)).astype(int)\n");
        out.append("        for idx, latency in np.ndenumerate(latencies):\n");
        out.append("            seq[int(latency)][idx] = 1.0\n");
        out.append("        return seq\n");
        out.append("\n");
        out.append("    raise ValueError(f\"Unsupported encoding mode: {cfg.mode}\")\n\n");

        out.append("def _to_scalar(value):\n");
        out.append("    if isinstance(value, torch.Tensor):\n");
        out.append("        if value.numel() == 0:\n");
        out.append("            return 0.0\n");
        out.append("        return float(value.detach().float().mean().item())\n");
        out.append("    arr = np.asarray(value, dtype=np.float32)\n");
        out.append("    if arr.size == 0:\n");
        out.append("        return 0.0\n");
        out.append("    return float(np.mean(arr))\n\n");

        out.append("def _node_step(node, input_value):\n");
        out.append("    if hasattr(node, \"receive_spike\") and callable(getattr(node, \"receive_spike\")):\n");
        out.append("        node.receive_spike(_to_scalar(input_value))\n");
        out.append("    if hasattr(node, \"spike_rule\") and callable(getattr(node, \"spike_rule\")):\n");
        out.append("        node.spike_rule()\n");
        out.append("    elif hasattr(node, \"time_step\") and callable(getattr(node, \"time_step\")):\n");
        out.append("        node.time_step()\n");
        out.append("\n");
        out.append("    if hasattr(node, \"readout\") and callable(getattr(node, \"readout\")):\n");
        out.append("        maybe = node.readout()\n");
        out.append("        if maybe is not None:\n");
        out.append("            return maybe\n");
        out.append("\n");
        out.append("    if hasattr(node, \"forward\") and callable(getattr(node, \"forward\")):\n");
        out.append("        return node.forward(input_value)\n");
        out.append("\n");
        out.append("    return input_value\n\n");

        out.append("def _entry_nodes(connections, node_map):\n");
        out.append("    in_degree = {name: 0 for name in node_map.keys()}\n");
        out.append("    for _src, dst, _w in connections:\n");
        out.append("        if dst in in_degree:\n");
        out.append("            in_degree[dst] += 1\n");
        out.append("    roots = [name for name, degree in in_degree.items() if degree == 0]\n");
        out.append("    return roots if roots else list(node_map.keys())\n\n");

        out.append("def _run_single_sample(encoded_sequence, node_map, connections, explicit_entry=None):\n");
        out.append("    entries = [explicit_entry] if explicit_entry else _entry_nodes(connections, node_map)\n");
        out.append("    sample_outputs = []\n");
        out.append("\n");
        out.append("    for tick in encoded_sequence:\n");
        out.append("        activations = {name: tick for name in entries if name in node_map}\n");
        out.append("        for node_name in list(node_map.keys()):\n");
        out.append("            if node_name not in activations:\n");
        out.append("                continue\n");
        out.append("            node_out = _node_step(node_map[node_name], activations[node_name])\n");
        out.append("            for src, dst, weight in connections:\n");
        out.append("                if src != node_name:\n");
        out.append("                    continue\n");
        out.append("                weighted = _to_scalar(node_out) * float(weight)\n");
        out.append("                if dst in activations:\n");
        out.append("                    activations[dst] = _to_scalar(activations[dst]) + weighted\n");
        out.append("                else:\n");
        out.append("                    activations[dst] = weighted\n");
        out.append("\n");
        out.append("        if READOUT_NODES:\n");
        out.append("            tick_out = {name: _node_step(node_map[name], activations.get(name, 0.0)) for name in READOUT_NODES if name in node_map}\n");
        out.append("        else:\n");
        out.append("            sinks = [dst for _src, dst, _w in connections]\n");
        out.append("            tick_out = {name: activations[name] for name in activations if name not in sinks}\n");
        out.append("        sample_outputs.append(tick_out)\n");
        out.append("\n");
        out.append("    return sample_outputs\n\n");

        out.append("simulation_outputs = []\n");
        if (simulationSpec != null) {
            out.append("simulation_config = RDLDatasetConfig(")
                .append("mode=\"")
                .append(escapePython(simulationSpec.encoding))
                .append("\", ")
                .append("sim_steps=")
                .append(simulationSpec.steps)
                .append(", encode_steps=")
                .append(simulationSpec.encodeSteps)
                .append(", batch_size=")
                .append(simulationSpec.batchSize)
                .append(")\n");

            out.append("simulation_data = ").append(simulationSpec.dataName).append("\n");
            out.append("simulation_rows = simulation_data.head(simulation_config.batch_size)\n");
            out.append("for _, row in simulation_rows.iterrows():\n");
            out.append("    encoded = _encode_sample(row, simulation_config)\n");
            out.append("    sample_out = _run_single_sample(encoded, NODE_INSTANCES, GRAPH_CONNECTIONS");
            if (simulationSpec.entryName != null) {
                out.append(", explicit_entry=\"").append(escapePython(simulationSpec.entryName)).append("\"");
            }
            out.append(")\n");
            out.append("    simulation_outputs.append(sample_out)\n");
        } else {
            out.append("simulation_config = RDLDatasetConfig()\n");
            out.append("# No simulate block found in source DSL.\n");
        }

        if (saveTarget != null) {
            out.append("\n");
            out.append("torch.save({\n");
            out.append("    \"target\": \"")
                .append(escapePython(saveTarget))
                .append("\",\n");
            out.append("    \"outputs\": simulation_outputs,\n");
            out.append("    \"connections\": GRAPH_CONNECTIONS,\n");
            out.append("    \"encoding\": simulation_config.mode,\n");
            out.append("}, \"")
                .append(escapePython(saveTarget))
                .append(".pt\")\n");
        }

        return out.toString();
    }

    private String loadPreludeText() {
        Path preludePath = Path.of("LSM_source_copy.py");
        try {
            String prelude = Files.readString(preludePath);
            int mainIndex = prelude.indexOf("if __name__ == \"__main__\":");
            if (mainIndex >= 0) {
                prelude = prelude.substring(0, mainIndex);
            }
            return prelude.stripTrailing();
        } catch (Exception ex) {
            throw new RuntimeException("Unable to read ReservoirDL/LSM_source_copy.py for Python generation", ex);
        }
    }

    private void emitLsmClass(StringBuilder out, LsmSpec lsm) {
        out.append("class ").append(lsm.name).append("(LSM):\n");
        out.append("    def __init__(self, size=").append(lsm.size).append(", threshold=1.0, beta=0.9):\n");
        out.append("        super().__init__(size=size, threshold=threshold, beta=beta)\n");
        out.append("        self.name = \"").append(lsm.name).append("\"\n");

        for (RDLParser.AssignmentContext assign : lsm.initAssignments) {
            String left = normalizeIdentifier(assign.name().getText(), new LinkedHashSet<>(), lsm.knownFields);
            String right = normalizeExpr(assign.expr().getText(), new LinkedHashSet<>(), lsm.knownFields);
            out.append("        ").append(left).append(" = ").append(right).append("\n");
        }
        out.append("\n");

        boolean emittedMethod = false;
        for (RDLParser.BlockStatementContext bs : lsm.methodStatements) {
            if (bs.methodDecl() != null) {
                emittedMethod = true;
                emitLsmMethod(out, bs.methodDecl(), lsm.knownFields);
            } else if (bs.spikeDecl() != null) {
                emittedMethod = true;
                emitLsmSpikeMethod(out, bs.spikeDecl(), lsm.knownFields);
            }
        }

        if (!emittedMethod) {
            out.append("    def spike_rule(self):\n");
            out.append("        parent = getattr(super(), \"time_step\", None)\n");
            out.append("        if callable(parent):\n");
            out.append("            return parent()\n");
            out.append("        return None\n\n");
        }
    }

    private void emitLayerClass(StringBuilder out, LayerSpec layer) {
        String baseClass = layer.readout ? "Answer" : "Layer";
        out.append("class ").append(layer.name).append("(").append(baseClass).append("):\n");
        out.append("    def __init__(self):\n");
        out.append("        super().__init__(name=\"").append(layer.name).append("\"");
        if (layer.size != null) {
            out.append(", size=").append(layer.size);
        } else {
            out.append(", size=None");
        }
        if (layer.type != null) {
            out.append(", layer_type=\"").append(escapePython(layer.type)).append("\"");
        } else {
            out.append(", layer_type=None");
        }
        out.append(")\n");

        for (RDLParser.AssignmentContext assign : layer.initAssignments) {
            String left = normalizeIdentifier(assign.name().getText(), new LinkedHashSet<>(), layer.knownFields);
            String right = normalizeExpr(assign.expr().getText(), new LinkedHashSet<>(), layer.knownFields);
            out.append("        ").append(left).append(" = ").append(right).append("\n");
        }
        out.append("\n");

        boolean emittedMethod = false;
        for (RDLParser.BlockStatementContext bs : layer.methodStatements) {
            if (bs.methodDecl() != null) {
                emittedMethod = true;
                emitLayerMethod(out, bs.methodDecl(), layer.knownFields);
            } else if (bs.spikeDecl() != null) {
                emittedMethod = true;
                emitLayerSpikeMethod(out, bs.spikeDecl(), layer.knownFields);
            }
        }

        if (!emittedMethod) {
            out.append("    def forward(self, x):\n");
            out.append("        parent = getattr(super(), \"forward\", None)\n");
            out.append("        if callable(parent):\n");
            out.append("            parent(x)\n");
            out.append("        return x\n\n");

            out.append("    def readout(self):\n");
            out.append("        parent = getattr(super(), \"readout\", None)\n");
            out.append("        if callable(parent):\n");
            out.append("            parent()\n");
            out.append("        return None\n\n");
        }
    }
    
    private void emitLsmMethod(StringBuilder out, RDLParser.MethodDeclContext ctx, Set<String> knownFields) {
        String methodName = ctx.methodName().getText();
        List<String> parameters = new ArrayList<>();
        Set<String> locals = new LinkedHashSet<>();

        if (ctx.paramList() != null) {
            for (RDLParser.NameContext n : ctx.paramList().name()) {
                String parameter = n.getText();
                parameters.add(parameter);
                locals.add(parameter);
            }
        }

        out.append("    def ").append(methodName).append("(self");
        for (String parameter : parameters) {
            out.append(", ").append(parameter);
        }
        out.append("):\n");

        out.append("        parent = getattr(super(), \"").append(methodName).append("\", None)\n");
        out.append("        if callable(parent):\n");
        out.append("            parent(");
        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) {
                out.append(", ");
            }
            out.append(parameters.get(i));
        }
        out.append(")\n");

        List<String> body = renderBlockStatements(ctx.block().blockStatement(), 2, locals, knownFields);
        if (body.isEmpty()) {
            out.append("        return None\n\n");
        } else {
            for (String line : body) {
                out.append(line).append("\n");
            }
            out.append("\n");
        }

        if ("spike".equals(methodName)) {
            out.append("    def spike_rule(self):\n");
            out.append("        return self.spike()\n\n");
        }
    }

    private void emitLsmSpikeMethod(StringBuilder out, RDLParser.SpikeDeclContext ctx, Set<String> knownFields) {
        out.append("    def spike(self):\n");
        out.append("        parent = getattr(super(), \"spike\", None)\n");
        out.append("        if callable(parent):\n");
        out.append("            parent()\n");

        List<String> body = renderBlockStatements(ctx.block().blockStatement(), 2, new LinkedHashSet<>(), knownFields);
        if (body.isEmpty()) {
            out.append("        return None\n\n");
        } else {
            for (String line : body) {
                out.append(line).append("\n");
            }
            out.append("\n");
        }

        out.append("    def spike_rule(self):\n");
        out.append("        return self.spike()\n\n");
    }

    private void emitLayerMethod(StringBuilder out, RDLParser.MethodDeclContext ctx, Set<String> knownFields) {
        String methodName = ctx.methodName().getText();
        List<String> parameters = new ArrayList<>();
        Set<String> locals = new LinkedHashSet<>();

        if (ctx.paramList() != null) {
            for (RDLParser.NameContext n : ctx.paramList().name()) {
                String parameter = n.getText();
                parameters.add(parameter);
                locals.add(parameter);
            }
        }

        out.append("    def ").append(methodName).append("(self");
        for (String parameter : parameters) {
            out.append(", ").append(parameter);
        }
        out.append("):\n");

        out.append("        parent = getattr(super(), \"").append(methodName).append("\", None)\n");
        out.append("        if callable(parent):\n");
        out.append("            parent(");
        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) {
                out.append(", ");
            }
            out.append(parameters.get(i));
        }
        out.append(")\n");

        List<String> body = renderBlockStatements(ctx.block().blockStatement(), 2, locals, knownFields);
        if (body.isEmpty()) {
            out.append("        return None\n\n");
        } else {
            for (String line : body) {
                out.append(line).append("\n");
            }
            if ("forward".equals(methodName) || "readout".equals(methodName)) {
                out.append("        return getattr(self, \"spike\", None)\n");
            }
            out.append("\n");
        }
    }

    private void emitLayerSpikeMethod(StringBuilder out, RDLParser.SpikeDeclContext ctx, Set<String> knownFields) {
        out.append("    def spike(self):\n");
        out.append("        parent = getattr(super(), \"spike\", None)\n");
        out.append("        if callable(parent):\n");
        out.append("            parent()\n");

        List<String> body = renderBlockStatements(ctx.block().blockStatement(), 2, new LinkedHashSet<>(), knownFields);
        if (body.isEmpty()) {
            out.append("        return None\n\n");
        } else {
            for (String line : body) {
                out.append(line).append("\n");
            }
            out.append("\n");
        }
    }

    private List<String> renderBlockStatements(
            List<RDLParser.BlockStatementContext> statements,
            int indentLevel,
            Set<String> localNames,
            Set<String> knownFields) {

        List<String> lines = new ArrayList<>();
        String indent = indent(indentLevel);

        for (RDLParser.BlockStatementContext bs : statements) {
            if (bs.ifStmt() != null) {
                RDLParser.IfStmtContext ifStmt = bs.ifStmt();
                String cond = normalizeExpr(ifStmt.expr().getText(), localNames, knownFields);
                lines.add(indent + "if " + cond + ":");
                List<String> nested = renderBlockStatements(ifStmt.block().blockStatement(), indentLevel + 1, localNames, knownFields);
                if (nested.isEmpty()) {
                    lines.add(indent + "    pass");
                } else {
                    lines.addAll(nested);
                }
            } else if (bs.emitStmt() != null) {
                lines.add(indent + "parent = getattr(super(), \"send_spike\", None)");
                lines.add(indent + "if callable(parent):");
                lines.add(indent + "    parent()");
            } else if (bs.assignment() != null) {
                String left = normalizeIdentifier(bs.assignment().name().getText(), localNames, knownFields);
                String right = normalizeExpr(bs.assignment().expr().getText(), localNames, knownFields);
                lines.add(indent + left + " = " + right);
            } else if (bs.augmentedAssignment() != null) {
                lines.add(indent + renderAugmentedAssignment(bs.augmentedAssignment(), localNames, knownFields));
            } else if (bs.incrementStmt() != null) {
                String target = normalizeIdentifier(bs.incrementStmt().name().getText(), localNames, knownFields);
                lines.add(indent + target + " += 1");
            }
        }

        return lines;
    }

    private String renderAugmentedAssignment(
            RDLParser.AugmentedAssignmentContext ctx,
            Set<String> localNames,
            Set<String> knownFields) {

        String left = normalizeIdentifier(ctx.name().getText(), localNames, knownFields);
        String operator = ctx.op.getText();
        String right = normalizeExpr(ctx.expr().getText(), localNames, knownFields);
        return left + " " + operator + " " + right;
    }

    private String normalizeExpr(String expr, Set<String> localNames, Set<String> knownFields) {
        String out = expr;
        out = replaceWholeWord(out, "mem_potential", "self.mem_potential");
        out = replaceWholeWord(out, "threshold", "self.threshold");
        out = replaceWholeWord(out, "spike", "self.spike_state");

        if (knownFields != null) {
            for (String field : knownFields) {
                if (localNames != null && localNames.contains(field)) {
                    continue;
                }
                if ("mem_potential".equals(field) || "threshold".equals(field) || "spike".equals(field)) {
                    continue;
                }
                out = replaceWholeWord(out, field, "self." + field);
            }
        }

        return out;
    }

    private String normalizeIdentifier(String id, Set<String> localNames, Set<String> knownFields) {
        if ("mem_potential".equals(id)) {
            return "self.mem_potential";
        }
        if ("threshold".equals(id)) {
            return "self.threshold";
        }
        if ("spike".equals(id)) {
            return "self.spike_state";
        }
        if (localNames != null && localNames.contains(id)) {
            return id;
        }
        if (knownFields != null && knownFields.contains(id)) {
            return "self." + id;
        }
        return "self." + id;
    }

    private String joinTypeValue(RDLParser.TypeValueContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(ctx.getChild(i).getText());
        }
        return sb.toString();
    }

    private String indent(int level) {
        return "    ".repeat(Math.max(0, level));
    }

    private String unquote(String quoted) {
        if (quoted == null || quoted.length() < 2) {
            return quoted;
        }
        return quoted.substring(1, quoted.length() - 1);
    }

    private String escapePython(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private String replaceWholeWord(String source, String find, String replace) {
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(find) + "\\b");
        Matcher matcher = pattern.matcher(source);
        return matcher.replaceAll(replace);
    }
}
