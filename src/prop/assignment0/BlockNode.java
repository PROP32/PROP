package prop.assignment0;

public class BlockNode implements INode{
    private Lexeme left_curly;
    private Lexeme right_curly;
    private StatementsNode statement;

    public BlockNode(Lexeme left_curly, Lexeme right_curly, StatementsNode statement) {
        this.left_curly = left_curly;
        this.right_curly = right_curly;
        this.statement = statement;
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        appendTabs(builder, tabs);
        builder.append("BlockNode\n");

        appendTabs(builder, tabs);
        builder.append(left_curly.toString() + "\n");

        if (statement != null) {
            statement.buildString(builder, tabs+1);
        }
        appendTabs(builder, tabs);
        builder.append(right_curly.toString() + "\n");
    }

    private void appendTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; ++i)
        {
            builder.append("\t");
        }
    }
}
