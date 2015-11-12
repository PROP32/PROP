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
                tk.open(inputFileName);
                while(tk.charclass != tk.EOF){
                    tk.lex();
                }
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
