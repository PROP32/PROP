package prop.assignment0;

import java.util.HashMap;

public class ExpressionNode implements INode {
    // <expr> -> <term>, [('+' | '-'), <expr>]
    private Lexeme operator = null;
    private ExpressionNode sub_expression = null;
    private TermNode term = null;

    public ExpressionNode(TermNode term) {
        this.term = term;
    }

    public ExpressionNode(TermNode term, Lexeme operator, ExpressionNode sub_expression) {
        this.term = term;
        this.operator = operator;
        this.sub_expression = sub_expression;
    }

    @Override
    public Object evaluate(HashMap args) throws Exception {
        double left = Double.parseDouble(term.evaluate(args).toString());
        if(operator != null){
            double right = Double.parseDouble(sub_expression.evaluate(args).toString());
            if(operator.token() == Token.ADD_OP){
                return left + right;
            }else if(operator.token() == Token.SUB_OP){
                return left - right;
            }
        }
        return left;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        appendTabs(builder, tabs);
        builder.append("ExpressionNode\n");

        if (term != null) {
            term.buildString(builder, tabs + 1);
        }
        if (operator != null) {
            appendTabs(builder, tabs + 1);
            builder.append(operator.toString() + "\n");
        }
        if (sub_expression != null) {
            sub_expression.buildString(builder, tabs + 1);
        }
    }

    private void appendTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; ++i)
        {
            builder.append("\t");
        }
    }

}
