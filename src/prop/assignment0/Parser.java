package prop.assignment0;

import java.io.IOException;

public class Parser implements IParser {
    Tokenizer tk;

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        tk.open(fileName);
    }

    @Override
    public INode parse() throws IOException, TokenizerException, ParserException {
        return parseAssignment();
    }

    @Override
    public void close() throws IOException {
        tk.close();
    }

    //TODO Get a list of tokens from Tokenizer
    public INode parseAssignment(){
        INode id = parseIdentifier();
        // check '='
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
