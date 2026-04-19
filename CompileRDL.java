import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class CompileRDL {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: java CompileRDL <input.rc> [output.py]");
            System.exit(1);
        }

        String inputPath = args[0];
        String outputPath = args.length > 1 ? args[1] : "generated_lsm.py";

        RDLLexer lexer = new RDLLexer(CharStreams.fromFileName(inputPath));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RDLParser parser = new RDLParser(tokens);

        ParseTree tree = parser.program();
        RDLToPythonVisitor visitor = new RDLToPythonVisitor();
        String pythonCode = visitor.visit(tree);

        Files.writeString(Path.of(outputPath), pythonCode, StandardCharsets.UTF_8);
        System.out.println("Generated Python: " + outputPath);
    }
}
