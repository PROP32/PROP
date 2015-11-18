package prop.assignment0;

public class StatementsNode implements INode{
    private AssignmentNode assignment;
    private StatementsNode next_statement;

    public StatementsNode() {
        this.assignment = null;
        this.next_statement = null;
    }

    public  StatementsNode(AssignmentNode left_assignment, StatementsNode statements) {
        this.assignment = left_assignment;
        this.next_statement = statements;
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        appendTabs(builder, tabs);
        builder.append("StatementsNode\n");

        if (assignment != null) {
            assignment.buildString(builder, tabs + 1);
        }
        if (next_statement != null) {
            next_statement.buildString(builder, tabs + 1);
        }
    }

    private void appendTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; ++i)
        {
            builder.append("\t");
        }
    }
}
