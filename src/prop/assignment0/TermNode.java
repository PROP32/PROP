package prop.assignment0;

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
    public Object evaluate(Object[] args) throws Exception {
        return null;
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
