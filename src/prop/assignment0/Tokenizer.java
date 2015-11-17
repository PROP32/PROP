package prop.assignment0;

import java.io.IOException;
import java.util.ArrayList;

public class Tokenizer implements ITokenizer {

    Scanner sc = new Scanner();
    char currentchar;
    String value;
    Token token;

    String stream = "";
    char[] chars = new char[100];
    int charslength;

    int charclass;

    //Character classes
    final int LETTER = 0;
    final int DIGIT = 1;
    final int UNKNOWN = 99;
    final int EOF = -1;

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        sc.open(fileName);
        getChar();
    }

    @Override
    public Lexeme current() {
        tokenize();
        Lexeme lex = new Lexeme(value,token);
        return lex;
    }

    @Override
    public void moveNext() throws IOException, TokenizerException {
        sc.moveNext();
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
        currentchar = sc.current();
        if(sc.current() != Scanner.EOF){
            if(Character.isLetter(currentchar)){
                charclass = LETTER;
            }
            else if(Character.isDigit(currentchar)){
                charclass = DIGIT;
            }
            else charclass = UNKNOWN;
        }
        else
            charclass = EOF; // Not sure
    }

    // A method to add nextChar to lexeme
    public void addChar(){
        if(charslength <= 98){
            chars[charslength++] = currentchar;
            chars[charslength] = 0;
        }
        else
            System.out.print("Error - lexeme is too long \n");
    }

    // A method to call getChar until it returns a non-whitespace character
    public void getNonBlank(){
        while(Character.isSpaceChar(currentchar)){
            getChar();
        }
    }
    //TODO Use regex patterns instead?
    public void lookup(char ch){
        switch (ch){
            case '(':
                addChar();
                token = Token.LEFT_PAREN;
                break;
            case ')':
                addChar();
                token = Token.RIGHT_PAREN;
                break;
            case '+':
                addChar();
                token = Token.ADD_OP;
                break;
            case '-':
                addChar();
                token = Token.SUB_OP;
                break;
            case '*':
                addChar();
                token = Token.MULT_OP;
                break;
            case '/':
                addChar();
                token = Token.DIV_OP;
                break;
            case '=':
                addChar();
                token = Token.ASSIGN_OP;
                break;
            case ';':
                addChar();
                token = Token.SEMICOLON;
                break;
            case '{':
                addChar();
                token = Token.LEFT_CURLY;
                break;
            case '}':
                addChar();
                token = Token.RIGHT_CURLY;
                break;
            default:
                addChar();
                token = Token.EOF;
        }
    }

    public void tokenize(){
        charslength = 0;
        clearChars();
        getNonBlank();
        switch (charclass){
            // Parse identifiers
            case LETTER:
                addChar();
                getChar();
                while(charclass == LETTER){
                    addChar();
                    getChar();
                }
                token = Token.IDENT;
                break;

            // Parse integer literals
            case DIGIT:
                addChar();
                getChar();
                while (charclass == DIGIT){
                    addChar();
                    getChar();
                }
                token = Token.INT_LIT;
                break;

            // Parentheses and operators
            case UNKNOWN:
                lookup(currentchar);
                getChar();
                break;

            // EOF
            case EOF:
                token = Token.EOF;
                chars[0] = 'E';
                chars[1] = 'O';
                chars[2] = 'F';
                chars[3] = 0;
                break;
        } // Switch
        value = "";
        for(char ch : chars){
            if(!Character.isWhitespace(ch) && ch != 0){
                value = value + ch;
            }
        }
        stream = stream + value;
    }

    public void clearChars(){
        chars = new char[100];
    }
}