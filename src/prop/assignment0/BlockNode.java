package prop.assignment0;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;

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
    public Object evaluate(HashMap args) throws Exception {
        HashMap<String, Object> assigns = new HashMap<String, Object>();
        statement.evaluate(assigns).toString();
        String block_result = "";
        for (HashMap.Entry<String, Object> assign : assigns.entrySet()) {
            block_result += assign.getKey() + " = " + assign.getValue().toString() + "\n";
        }
        return block_result;
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
