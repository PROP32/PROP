package prop.assignment0;

public class AssignmentNode implements INode {
    private Lexeme identifier;
    private ExpressionNode expression;
    private Lexeme assign_op;
    private Lexeme semicolon;

    public AssignmentNode(Lexeme ident, Lexeme assign_op, ExpressionNode expr, Lexeme semicolon) {
        this.identifier = ident;
        this.assign_op = assign_op;
        this.expression = expr;
        this.semicolon = semicolon;
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        appendTabs(builder, tabs);
        builder.append("AssignmentNode\n");

        appendTabs(builder, tabs+1);
        builder.append(identifier.toString() + "\n");

        appendTabs(builder, tabs+1);
        builder.append(assign_op.toString() + "\n");

        if (expression != null) {
            expression.buildString(builder, tabs + 1);
        }

        appendTabs(builder, tabs+1);
        builder.append(semicolon.toString() + "\n");
    }

    private void appendTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; ++i)
        {
            builder.append("\t");
        }
    }
}
