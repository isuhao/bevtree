package ai.core.ext.condition;
import ai.core.NodeInputParam;
import ai.core.NodePreCondition;

/**
 * BevNodePreconditionTRUE
 * 
 */
public class NodePreConditionTRUE extends NodePreCondition {
	public Boolean evaluate(NodeInputParam input) {
		return true;
	}
}
