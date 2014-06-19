package ai.core.ext;

import ai.core.Node;
import ai.core.NodeInputParam;

/**
 * BevNodeSequenceSync
 * 
 */
public class NodeSequenceSync extends Node {

	public NodeSequenceSync(String debugName) {
		super(debugName);
	}

	protected Boolean doEvaluate(NodeInputParam input) {
		int len = children.size();
		for (int i = 0; i < len; ++i) {
			if (!children.get(i).evaluate(input)) {
				return false;
			}
		}

		return true;
	}

	protected int doTick(NodeInputParam input) {
		int isFinish = BRS_FINISH;

		if (_currentNodeIndex == -1)
			_currentNodeIndex = 0;

		while (true) {
			isFinish = children.get(_currentNodeIndex).tick(input);

			if (isFinish != BRS_FINISH)
				break;

			if (++_currentNodeIndex == children.size()) {
				_currentNodeIndex = -1;
				break;
			}
		}

		if (isFinish < 0) // error
			_currentNodeIndex = -1;
		return isFinish;
	}

	protected void doTransition(NodeInputParam input) {
		if (checkIndex(_currentNodeIndex))
			children.get(_currentNodeIndex).transition(input);
		_currentNodeIndex = -1;
	}

	private int _currentNodeIndex = -1;

}
