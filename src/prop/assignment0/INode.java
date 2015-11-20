package prop.assignment0;

import java.util.HashMap;

public interface INode {
	/**
	 * The argument array 'args' is only needed for the requirements for grade A and B.
	 * When not needed just call evaluate with null as the actual parameter.
	 */
	Object evaluate(HashMap args) throws Exception;
	
	void buildString(StringBuilder builder, int tabs);
}