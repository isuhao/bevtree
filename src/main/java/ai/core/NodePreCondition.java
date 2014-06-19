package ai.core;

/**
 * BevNodePrecondition
 * 
 */
public class NodePreCondition {

	public NodePreCondition() {
	}

	public Boolean evaluate(NodeInputParam input) {
		throw new Error(
				"This is an abstract method. You need to implement yourself.");
	}

}
