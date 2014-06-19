package ai.core;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 序列（Sequence）：将其所有子节点依次执行，也就是说当前一个返回“完成”状态后，再运行先一个子节点
 */
@XStreamAlias("NodeSequence")
public class NodeSequence extends Node {

	public NodeSequence(String debugName) {
		super(debugName);
	}

	protected Boolean doEvaluate(NodeInputParam input) {
		int index = currentNodeIndex;
		if (index == -1)
			index = 0;

		if (checkIndex(index)) {
			if (children.get(index).evaluate(input))
				return true;
		}

		return false;
	}

	protected int doTick(NodeInputParam input) {
		int isFinish = BRS_FINISH;

		if (currentNodeIndex == -1)
			currentNodeIndex = 0;

		isFinish = children.get(currentNodeIndex).tick(input);
		if (isFinish == BRS_FINISH) {
			++currentNodeIndex;
			if (currentNodeIndex == children.size())
				currentNodeIndex = -1;
			else
				isFinish = BRS_EXECUTING;
		}

		if (isFinish < 0) // error
			currentNodeIndex = -1;

		return isFinish;
	}

	protected void doTransition(NodeInputParam input) {
		if (checkIndex(currentNodeIndex))
			children.get(currentNodeIndex).transition(input);
		currentNodeIndex = -1;
	}

	private int currentNodeIndex = -1;

}