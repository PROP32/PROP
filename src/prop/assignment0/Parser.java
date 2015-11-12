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
        return null;
    }

    @Override
    public void close() throws IOException {
        tk.close();
    }
}
