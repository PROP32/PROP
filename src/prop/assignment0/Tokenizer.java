package prop.assignment0;

import java.io.IOException;

public class Tokenizer implements ITokenizer {

    private Scanner sc;
    private char nextChar;
    private Token nextToken;

    private char[] lexeme;
    private int lexlen;
    private Lexeme lex = null;

    int charclass;

    //Character classes
    final int LETTER = 0;
    final int DIGIT = 1;
    final int UNKNOWN = 99;
    final int EOF = -1;

    public Tokenizer(){
        this.sc = new Scanner();
        this.nextChar = ' ';
        this.lexeme = new char[100];
    }

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        sc.open(fileName);
    }

    @Override
    public Lexeme current() {
        return lex;
    }

    @Override
    public void moveNext() throws IOException, TokenizerException {
        this.lex();
        lex = new Lexeme(new String(lexeme), nextToken);
    }

    @Override
    public void close() throws IOException {
        sc.close();
    }

    public void getChar(){
        try {
            sc.moveNext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nextChar = sc.current();
        if(sc.current() != Scanner.EOF){
            if(Character.isLetter(nextChar)){
                charclass = LETTER;
            }
            else if(Character.isDigit(nextChar)){
                charclass = DIGIT;
            }
            else charclass = UNKNOWN;
        }
        else
            charclass = EOF; // Not sure
    }

    // A method to add nextChar to lexeme
    public void addChar(){
        if(lexlen <= 98){
            lexeme[lexlen++] = nextChar;
            lexeme[lexlen] = 0;
        }
        else
            System.out.print("Error - lexeme is too long \n");
    }

    // A method to call getChar until it returns a non-whitespace character
    public void getNonBlank(){
        while(Character.isSpaceChar(nextChar)){
            getChar();
        }
    }

    public void lookup(char ch){
        switch (ch){
            case '(':
                addChar();
                nextToken = Token.LEFT_PAREN;
                break;
            case ')':
                addChar();
                nextToken = Token.RIGHT_PAREN;
                break;
            case '+':
                addChar();
                nextToken = Token.ADD_OP;
                break;
            case '-':
                addChar();
                nextToken = Token.SUB_OP;
                break;
            case '*':
                addChar();
                nextToken = Token.MULT_OP;
                break;
            case '/':
                addChar();
                nextToken = Token.DIV_OP;
                break;
            case '=':
                addChar();
                nextToken = Token.ASSIGN_OP;
                break;
            case ';':
                addChar();
                nextToken = Token.SEMICOLON;
                break;
            case '{':
                addChar();
                nextToken = Token.LEFT_CURLY;
                break;
            case '}':
                addChar();
                nextToken = Token.RIGHT_CURLY;
                break;
            default:
                addChar();
                nextToken = Token.EOF;
        }
    }

    public void lex(){
        lexlen = 0;
        getNonBlank();
        switch (charclass){
            // Parse identifiers
            case LETTER:
                addChar();
                getChar();
                while(charclass == LETTER || charclass == DIGIT){
                    addChar();
                    getChar();
                }
                nextToken = Token.IDENT;
                break;

            // Parse integer literals
            case DIGIT:
                addChar();
                getChar();
                while (charclass == DIGIT){
                    addChar();
                    getChar();
                }
                nextToken = Token.INT_LIT;
                break;

            // Parentheses and operators
            case UNKNOWN:
                lookup(nextChar);
                getChar();
                break;

            // EOF
            case EOF:
                nextToken = Token.EOF;
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'F';
                lexeme[3] = 0;
                break;
        } // Switch

        //debug
        String output = "";
        for(char str : lexeme){
            output = output + str;
        }
        System.out.print("Token: "+nextToken+" \t\tLexeme: "+ output+"\n");
    }
}
