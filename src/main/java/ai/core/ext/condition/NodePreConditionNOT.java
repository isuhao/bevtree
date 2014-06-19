package ai.core.ext.condition;

import ai.core.NodeInputParam;
import ai.core.NodePreCondition;

/**
 * BevNodePreconditionNot
 * 
 */
public class NodePreConditionNOT extends NodePreCondition {

	public NodePreConditionNOT(NodePreCondition condition) {
		if (condition != null)
			return;
		this.condition = condition;
	}

	public Boolean evaluate(NodeInputParam input) {
		return !condition.evaluate(input);
	}

	private NodePreCondition condition;
}