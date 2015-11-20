package prop.assignment0;

import java.util.HashMap;

public class TermNode implements INode {
    private FactorNode factor = null;
    private Lexeme operator = null;
    private TermNode sub_term = null;

    public TermNode(FactorNode factor) {
        this.factor = factor;
    }

    public TermNode(FactorNode factor, Lexeme operator, TermNode sub_term) {
        this.factor = factor;
        this.operator = operator;
        this.sub_term = sub_term;
    }

    @Override
    public Object evaluate(HashMap args) throws Exception {
        double left = Double.parseDouble(factor.evaluate(args).toString());
        if(operator != null){
            double right = Double.parseDouble(sub_term.evaluate(args).toString());
            if(operator.token() == Token.MULT_OP){
                return left * right;
            }else if(operator.token() == Token.DIV_OP){
                return left / right;
            }
        }
        return left;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        appendTabs(builder, tabs);
        builder.append("TermNode\n");

        if (factor != null) {
            factor.buildString(builder, tabs + 1);
        }
        if (operator != null) {
            appendTabs(builder, tabs + 1);
            builder.append(operator.toString() + "\n");
        }
        if (sub_term != null) {
            sub_term.buildString(builder, tabs + 1);
        }
    }

    private void appendTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; ++i)
        {
            builder.append("\t");
        }
    }
}
