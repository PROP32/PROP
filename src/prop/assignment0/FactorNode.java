package prop.assignment0;

public class FactorNode implements INode {
    private Lexeme ident = null;
    private Lexeme left_paran = null;
    private Lexeme right_paran = null;
    private ExpressionNode expr = null;

    public FactorNode(Lexeme ident) {
        this.ident = ident;
    }

    public FactorNode(Lexeme left_paran, ExpressionNode expr, Lexeme right_paran) {
        this.left_paran = left_paran;
        this.expr = expr;
        this.right_paran = right_paran;
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        appendTabs(builder, tabs);
        builder.append("FactorNode\n");

        if (ident != null) {
            appendTabs(builder, tabs + 1);
            builder.append(ident.toString() + "\n");
        }
        if (expr != null) {
            appendTabs(builder, tabs+1);
            builder.append(left_paran.toString() + "\n");

            expr.buildString(builder, tabs + 1);

            appendTabs(builder, tabs+1);
            builder.append(right_paran.toString() + "\n");
        }
    }

    private void appendTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; ++i)
        {
            builder.append("\t");
        }
    }
}
