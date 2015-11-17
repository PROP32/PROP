package prop.assignment0;

public class ExpressionNode implements INode {
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
    public Object evaluate(Object[] args) throws Exception {
        return null;
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
