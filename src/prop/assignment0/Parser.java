package prop.assignment0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Parser implements IParser {
    Tokenizer tk = new Tokenizer();
    ArrayList<Lexeme> lexemes = new ArrayList<>();
    String stream = "";
    Pattern intpat = Pattern.compile("^[0-9]");
    Pattern idpat = Pattern.compile("^[a-z]+");


    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        tk.open(fileName);
    }

    @Override
    public INode parse() throws IOException, TokenizerException, ParserException {
        /*for(int i = 0; i < 20; i++){
            Lexeme l = tk.current();
            System.out.println("shitty token added " + l.token() + ": " + l.value());
            lexemes.add(l);
        }*/
        do{
            Lexeme l = tk.current();
            lexemes.add(l);
        }while(lexemes.get(lexemes.size()-1).token() != Token.EOF);
        System.out.println("lexemes = " + lexemes.size());
        buildStream();
        return new AssignmentNode();
        //return parseAssignment();
    }

    @Override
    public void close() throws IOException {
        tk.close();
    }

    public void buildStream(){
        for(Lexeme lex : lexemes){
            stream = stream + lex.value().toString();
        }
    }

    //TODO get a list of tokens from Tokenizer

    public INode parseAssignment(){
        INode id = parseIdentifier();
        // check'='
        INode exp = parseExpression();
        return null;
    }

    private INode parseExpression() {
        INode term = parseTerm();
        // check + or -
        return null;
    }

    private INode parseTerm() {
        INode factor = parseFactor();
        return null;
    }

    private INode parseFactor() {
        return null;
    }

    private INode parseIdentifier() {
        return null;
    }

}
