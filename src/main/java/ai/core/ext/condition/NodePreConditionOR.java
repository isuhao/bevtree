package ai.core.ext.condition;
import java.util.Vector;

import ai.core.NodeInputParam;
import ai.core.NodePreCondition;

/**
 * BevNodePreconditionOR
 * 
 */
public class NodePreConditionOR extends NodePreCondition {

	public NodePreConditionOR(NodePreCondition... rest) {
		if (rest.length < 2)
			return;

		list = new Vector<NodePreCondition>(rest.length);
		for (int i = 0; i < rest.length; ++i)
			list.set(i, rest[i]);
	}

	public Boolean evaluate(NodeInputParam input) {
		for (int i = 0, n = list.size(); i < n; ++i)
			if (list.get(i).evaluate(input))
				return true;

		return false;
	}

	private Vector<NodePreCondition> list;

}