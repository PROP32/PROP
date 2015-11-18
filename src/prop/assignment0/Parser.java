package prop.assignment0;

import java.io.IOException;
import java.util.ArrayList;

public class Parser implements IParser {
    Tokenizer tk = new Tokenizer();
    ArrayList<Lexeme> lexemes = new ArrayList<>();

    ArrayList<Lexeme> stream = new ArrayList<>();
    Lexeme nextLex;

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        tk.open(fileName);
    }

    @Override
    public INode parse() throws IOException, TokenizerException, ParserException {
        do{
            Lexeme l = tk.current();
            lexemes.add(l);
        }while(lexemes.get(lexemes.size()-1).token() != Token.EOF);
        System.out.println("lexemes = " + lexemes.size());
        buildStream();

        System.out.println(stream); // Just for testing
        System.out.println(nextLex); // Just for testing

        return parseAssignment();
    }

    @Override
    public void close() throws IOException {
        tk.close();
    }

    public void buildStream(){
        for(Lexeme lex : lexemes) {
            stream.add(lex);
        }
        nextLex = stream.get(0);
    }

    public void nextLexeme(){
        stream.remove(0);
        nextLex = stream.get(0);
        System.out.print(nextLex+"\n");
    }

    public INode parseAssignment(){
        // id, '=', expr, ';'
        if(parseIdentifier()){
            System.out.print("----AssignmentNode\n");
            nextLexeme();
            if(nextLex.token() == Token.ASSIGN_OP){
                nextLexeme();
                parseExpression();
                if(nextLex.token() == Token.SEMICOLON){
                    System.out.print("Great success!");
                    return new AssignmentNode();
                }else{
                    System.out.print("Incorrect expression!");
                }
            }
        }
        return null;
    }

    private void parseExpression() {
        // term , [ ( '+' | '-' ) , expr ]
        System.out.print("----ExpressionNode\n");
        parseTerm();
        if(nextLex.token() == Token.ADD_OP || nextLex.token() == Token.SUB_OP){
            nextLexeme();
            parseExpression();
        }
    }

    private void parseTerm() {
        // factor , [ ( '*' | '/' ) , expr ]
        System.out.print("----TermNode\n");
        parseFactor();
        if(nextLex.token() == Token.MULT_OP || nextLex.token() == Token.DIV_OP){
            nextLexeme();
            parseTerm();
        }
    }

    private void parseFactor() {
        // int | '(' , expr , ')'
        System.out.print("----FactorNode\n");
        if(nextLex.token() == Token.INT_LIT){
            nextLexeme();
        }else if(nextLex.token() == Token.LEFT_PAREN){
            nextLexeme();
            parseExpression();
            if(nextLex.token() == Token.RIGHT_PAREN){
                nextLexeme();
            }
        }
    }

    // Checks if the first symbols in our stream is an identifier
    private Boolean parseIdentifier() {
        if(nextLex.token() == Token.IDENT){
            return true;
        }else{
            return false;
        }
    }

}
