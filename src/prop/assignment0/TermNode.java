package prop.assignment0;

public class TermNode implements INode {

    // <term> -> <factor>, [('*' | '/'), <term>]

    FactorNode fn = new FactorNode();

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }

}
