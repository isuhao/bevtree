package ai.core.ext;

import ai.core.Node;
import ai.core.NodeInputParam;

/**
 * BevNodeLoop
 * 
 */
public class NodeLoop extends Node {

	public NodeLoop(String debugName) {
		super(debugName);
	}

	public NodeLoop setLoopCount(int n) {
		loopCount = n;
		return this;
	}

	protected Boolean doEvaluate(NodeInputParam input) {
		Boolean checkLoop = (loopCount == -1) || (_currentLoop < loopCount);

		if (!checkLoop)
			return false;

		if (checkIndex(0))
			if (children.get(0).evaluate(input))
				return true;

		return false;
	}

	protected int doTick(NodeInputParam input) {
		int isFinish = BRS_FINISH;

		if (checkIndex(0)) {
			isFinish = children.get(0).tick(input);

			if (isFinish == BRS_FINISH) {
				if (loopCount == -1)
					isFinish = BRS_EXECUTING;
				else {
					++_currentLoop;
					if (_currentLoop < loopCount)
						isFinish = BRS_EXECUTING;
				}
			}
		}

		if (isFinish == BRS_FINISH)
			_currentLoop = 0;

		System.out.println(_currentLoop);
		return isFinish;
	}

	protected void doTransition(NodeInputParam input) {
		if (checkIndex(0))
			children.get(0).transition(input);

		_currentLoop = 0;
	}

	private int loopCount = -1;
	private int _currentLoop;
}
