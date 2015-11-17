package prop.assignment0;

public class debugger {
    public static void main(String[] args) {
        String inputFileName = null;
        String outputFileName = null;
        Tokenizer tk = new Tokenizer();

        try {
            try {
                if (args.length < 2)
                    throw new Exception("Incorrect number of parameters to program.");
                inputFileName = args[0];
                outputFileName = args[1];
                Parser parser = new Parser();
                parser.open(inputFileName);
                INode root = parser.parse();
                System.out.print(parser.stream+" < här ska texten vara");
                //root.evaluate(null);
                //StringBuilder builder = new StringBuilder();
                //builder.append("PARSE TREE:\n");
                //root.buildString(builder, 0);
                //builder.append("\nEVALUATION:\n");

                //System.out.print(builder);

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
