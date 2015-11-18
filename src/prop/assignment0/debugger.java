package prop.assignment0;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class debugger {
    public static void main(String[] args) {
        String inputFileName = null;
        String outputFileName = null;
        IParser parser = null;
        INode root = null; // Root of the parse tree.
        StringBuilder builder = null;

        try {
            try {
                if (args.length < 2)
                    throw new Exception("Incorrect number of parameters to program.");
                inputFileName = args[0];
                outputFileName = args[1];

                parser = new Parser();
                parser.open(inputFileName);
                root = parser.parse();

                builder = new StringBuilder();
                builder.append("PARSE TREE:\n");
                root.buildString(builder, 0);
                builder.append("\nEVALUATION:\n");
                builder.append(root.evaluate(null));
            }
            catch (Exception exception) {
                System.out.println("EXCEPTION: " + exception);
            }
        }
        catch (Exception exception) {
            System.out.println("EXCEPTION: " + exception);
        }
    }
}
