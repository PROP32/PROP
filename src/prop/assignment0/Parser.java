package prop.assignment0;

import java.io.IOException;

public class Parser implements IParser {
    Tokenizer token;

    public Parser() {
        this.token = new Tokenizer();
    }

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        token.open(fileName);
    }

    @Override
    public INode parse() throws IOException, TokenizerException, ParserException {
        INode root = parseAssignment();

        return root;
    }

    @Override
    public void close() throws IOException {
        token.close();
    }

    INode parseAssignment(){
        Lexeme ident = parseIdent();

        moveCurrentLexeme();
        Lexeme assign_op = getCurrentLexeme();
        if (assign_op.token() == Token.ASSIGN_OP) {
           ; //TODO handle wrong result
        }

        ExpressionNode expr = parseExpression();

        Lexeme semicolon = getCurrentLexeme();
        if (semicolon.token() == Token.SEMICOLON) {
            ;//TODO handle wrong result
        }
        return new AssignmentNode(ident, assign_op, expr, semicolon);
    }

    Lexeme parseIdent() {
        moveCurrentLexeme();
        Lexeme lex = getCurrentLexeme();
        if (lex.token() == Token.IDENT) {
            return lex;
        }
        //TODO handle wrong result
        return lex;
    }

    ExpressionNode parseExpression() {
        TermNode term = parseTerm();

        Lexeme currentLexeme = getCurrentLexeme();
        if (currentLexeme.token() == Token.ADD_OP || currentLexeme.token() == Token.SUB_OP) {
            Lexeme operator = currentLexeme;
            ExpressionNode sub_expr = parseExpression();
            return  new ExpressionNode(term, operator, sub_expr);
        }
        else {
            return  new ExpressionNode(term);
        }
    }

    TermNode parseTerm(){
        FactorNode factor = parseFactor();

        moveCurrentLexeme();
        Lexeme currentLexeme = getCurrentLexeme();
        if (currentLexeme.token() == Token.MULT_OP || currentLexeme.token() == Token.DIV_OP) {
            Lexeme operator = currentLexeme;
            TermNode sub_term = parseTerm();
            return new TermNode(factor, operator, sub_term);
        }
        else {
            return new TermNode(factor);
        }
    }

    FactorNode parseFactor() {
        moveCurrentLexeme();
        Lexeme currentLexeme = getCurrentLexeme();
        if (currentLexeme.token() == Token.INT_LIT || currentLexeme.token() == Token.IDENT)  {
            return new FactorNode(currentLexeme);
        }
        else if (currentLexeme.token() == Token.LEFT_PAREN) {
            Lexeme left_paren = currentLexeme;
            ExpressionNode sub_expr = parseExpression();
            Lexeme right_paren = getCurrentLexeme();
            return new FactorNode(left_paren, sub_expr, right_paren);
        }
        else {
            return null;
        }
    }

    void moveCurrentLexeme(){
        try {
            token.moveNext();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (TokenizerException et) {
            et.printStackTrace();
        }
    }

    Lexeme getCurrentLexeme() {
       return token.current();
    }
}
