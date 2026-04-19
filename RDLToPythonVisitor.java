import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class RDLToPythonVisitor extends RDLBaseVisitor<String> {

    private static final class DataSource {
        String name;
        String csvPath;
    }

    private static final class LsmSpec {
        String name;
        List<RDLParser.BlockStatementContext> blockStatements = new ArrayList<>();
    }

    private static final class LayerSpec {
        String name;
        boolean torchLayer;
        Integer size;
        String type;
    }

    private static final class ConnectionSpec {
        String from;
        String to;
        String weight;
    }

    private final Map<String, DataSource> dataSources = new LinkedHashMap<>();
    private final Map<String, LsmSpec> lsmSpecs = new LinkedHashMap<>();
    private final Map<String, LayerSpec> layerSpecs = new LinkedHashMap<>();
    private final List<ConnectionSpec> connections = new ArrayList<>();

    private String simulateDataName;
    private int simulateSteps = 1;
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
        spec.blockStatements.addAll(ctx.block().blockStatement());
        lsmSpecs.put(spec.name, spec);
        return null;
    }

    @Override
    public String visitLayerDecl(RDLParser.LayerDeclContext ctx) {
        LayerSpec spec = new LayerSpec();
        spec.torchLayer = ctx.TORCH() != null;
        spec.name = ctx.ID().getText();

        for (RDLParser.BlockStatementContext bs : ctx.block().blockStatement()) {
            if (bs.sizeStmt() != null) {
                spec.size = Integer.parseInt(bs.sizeStmt().INT().getText());
            }
            if (bs.typeStmt() != null) {
                spec.type = joinTypeValue(bs.typeStmt().typeValue());
            }
        }

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
        simulateDataName = ctx.ID().getText();
        for (RDLParser.BlockStatementContext bs : ctx.block().blockStatement()) {
            if (bs.stepsStmt() != null) {
                simulateSteps = Integer.parseInt(bs.stepsStmt().INT().getText());
            }
        }
        return null;
    }

    @Override
    public String visitSaveStmt(RDLParser.SaveStmtContext ctx) {
        saveTarget = ctx.ID().getText();
        return null;
    }

    private String buildPython() {
        StringBuilder out = new StringBuilder();

        out.append("import torch\n");
        out.append("import snntorch as snn\n");
        out.append("import numpy as np\n");
        out.append("import pandas as pd\n\n");

        out.append("class Neuron:\n");
        out.append("    def __init__(self, beta=0.9):\n");
        out.append("        self.neuron = snn.Leaky(beta=beta)\n");
        out.append("        self.connections = []\n");
        out.append("        self.weights = []\n");
        out.append("        self.history = []\n");
        out.append("        self.input_spikes = torch.tensor(0.0)\n");
        out.append("        self.spike = torch.tensor(0.0)\n");
        out.append("        self.mem = torch.tensor(0.0)\n\n");

        out.append("    def add_connection(self, n, weight=0.7, randomize_weight=True):\n");
        out.append("        self.connections.append(n)\n");
        out.append("        self.weights.append(np.random.random() if randomize_weight else weight)\n\n");

        out.append("    def add_connections(self, n_arr):\n");
        out.append("        for n in n_arr:\n");
        out.append("            self.add_connection(n)\n\n");

        out.append("    def receive_spike(self, value):\n");
        out.append("        self.input_spikes += torch.tensor(value)\n\n");

        out.append("    def send_spike(self):\n");
        out.append("        for i, c in enumerate(self.connections):\n");
        out.append("            c.receive_spike(self.spike * min(self.weights[i], 0.3))\n\n");

        out.append("    def time_step(self):\n");
        out.append("        self.spike, self.mem = self.neuron(self.input_spikes, self.mem)\n");
        out.append("        self.history.append({\n");
        out.append("            \"input\": self.input_spikes,\n");
        out.append("            \"spike\": self.spike,\n");
        out.append("            \"mem\": self.mem\n");
        out.append("        })\n");
        out.append("        self.input_spikes = torch.tensor(0.0)\n\n");

        out.append("class LSM(Neuron):\n");
        out.append("    def __init__(self, threshold=1.0, beta=0.9):\n");
        out.append("        super().__init__(beta=beta)\n");
        out.append("        self.threshold = threshold\n\n");

        out.append("class Layer:\n");
        out.append("    def __init__(self, name, size=None, layer_type=None):\n");
        out.append("        self.name = name\n");
        out.append("        self.size = size\n");
        out.append("        self.layer_type = layer_type\n");
        out.append("        self.outgoing = []\n\n");

        for (DataSource ds : dataSources.values()) {
            out.append(ds.name).append(" = pd.read_csv(\"").append(escapePython(ds.csvPath)).append("\")\n");
        }
        if (!dataSources.isEmpty()) {
            out.append("\n");
        }

        for (LsmSpec lsm : lsmSpecs.values()) {
            out.append("class ").append(lsm.name).append("(LSM):\n");
            out.append("    def __init__(self, threshold=1.0, beta=0.9):\n");
            out.append("        super().__init__(threshold=threshold, beta=beta)\n\n");

            RDLParser.SpikeDeclContext spikeDecl = null;
            for (RDLParser.BlockStatementContext bs : lsm.blockStatements) {
                if (bs.spikeDecl() != null) {
                    spikeDecl = bs.spikeDecl();
                    break;
                }
            }

            if (spikeDecl == null) {
                out.append("    def spike_rule(self):\n");
                out.append("        self.time_step()\n");
                out.append("\n");
                continue;
            }

            out.append("    def spike_rule(self):\n");
            List<String> body = renderBlockStatements(spikeDecl.block().blockStatement(), 2);
            if (body.isEmpty()) {
                out.append("        pass\n");
            } else {
                for (String line : body) {
                    out.append(line).append("\n");
                }
            }
            out.append("\n");
        }

        for (LayerSpec layer : layerSpecs.values()) {
            out.append("class ").append(layer.name).append("(Layer):\n");
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
            out.append("\n");
        }

        if (!lsmSpecs.isEmpty()) {
            for (String name : lsmSpecs.keySet()) {
                out.append(name).append("_instance = ").append(name).append("()\n");
            }
        }
        if (!layerSpecs.isEmpty()) {
            for (String name : layerSpecs.keySet()) {
                out.append(name).append("_instance = ").append(name).append("()\n");
            }
        }

        if (!connections.isEmpty()) {
            out.append("\n");
            out.append("connections = []\n");
            for (ConnectionSpec c : connections) {
                out.append("connections.append((\"")
                    .append(c.from)
                    .append("\", \"")
                    .append(c.to)
                    .append("\", ")
                    .append(c.weight)
                    .append("))\n");
            }
            out.append("\n");
        }

        out.append("# Simulation\n");
        out.append("simulation_data = None\n");
        if (simulateDataName != null) {
            out.append("simulation_data = ").append(simulateDataName).append("\n");
        }
        out.append("for _ in range(").append(simulateSteps).append("):\n");

        if (!lsmSpecs.isEmpty()) {
            for (String name : lsmSpecs.keySet()) {
                out.append("    ").append(name).append("_instance.spike_rule()\n");
            }
        } else {
            out.append("    pass\n");
        }

        if (saveTarget != null) {
            out.append("\n");
            out.append("# Save target artifact\n");
            out.append("torch.save({\"state\": \"")
                .append(escapePython(saveTarget))
                .append("\"}, \"")
                .append(escapePython(saveTarget))
                .append(".pt\")\n");
        }

        return out.toString();
    }

    private List<String> renderBlockStatements(List<RDLParser.BlockStatementContext> statements, int indentLevel) {
        List<String> lines = new ArrayList<>();
        String indent = indent(indentLevel);

        for (RDLParser.BlockStatementContext bs : statements) {
            if (bs.ifStmt() != null) {
                RDLParser.IfStmtContext ifStmt = bs.ifStmt();
                String cond = normalizeExpr(ifStmt.expr().getText());
                lines.add(indent + "if " + cond + ":");
                List<String> nested = renderBlockStatements(ifStmt.block().blockStatement(), indentLevel + 1);
                if (nested.isEmpty()) {
                    lines.add(indent + "    pass");
                } else {
                    lines.addAll(nested);
                }
            } else if (bs.emitStmt() != null) {
                lines.add(indent + "self.send_spike()");
            } else if (bs.assignment() != null) {
                String left = normalizeIdentifier(bs.assignment().ID().getText());
                String right = normalizeExpr(bs.assignment().expr().getText());
                lines.add(indent + left + " = " + right);
            }
        }

        return lines;
    }

    private String normalizeExpr(String expr) {
        String out = expr;
        out = replaceWholeWord(out, "mem_potential", "self.mem");
        out = replaceWholeWord(out, "threshold", "self.threshold");
        out = replaceWholeWord(out, "spike", "self.spike");
        return out;
    }

    private String normalizeIdentifier(String id) {
        if ("mem_potential".equals(id)) {
            return "self.mem";
        }
        if ("threshold".equals(id)) {
            return "self.threshold";
        }
        if ("spike".equals(id)) {
            return "self.spike";
        }
        return id;
    }

    private String joinTypeValue(RDLParser.TypeValueContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.ID().size(); i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(ctx.ID(i).getText());
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
        return Pattern.compile("\\b" + Pattern.quote(find) + "\\b").matcher(source).replaceAll(replace);
    }
}
