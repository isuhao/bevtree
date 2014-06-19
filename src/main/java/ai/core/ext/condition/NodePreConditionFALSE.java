package ai.core.ext.condition;
import ai.core.NodeInputParam;
import ai.core.NodePreCondition;

/**
 * BevNodePreconditionFALSE
 * 
 */
public class NodePreConditionFALSE extends NodePreCondition {

	public Boolean evaluate(NodeInputParam input) {
		return false;
	}
}