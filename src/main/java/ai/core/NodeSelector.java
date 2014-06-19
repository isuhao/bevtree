package ai.core;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 选择（Selector）选择其子节点的某一个执行
 */
@XStreamAlias("NodeSelector")
public class NodeSelector extends Node {

	public NodeSelector(String debugName) {
		super(debugName);
	}

	protected Boolean doEvaluate(NodeInputParam input) {
		currentSelectedIndex = -1;

		int len = children.size();
		for (int i = 0; i < len; ++i) {
			if (children.get(i).evaluate(input)) {
				currentSelectedIndex = i;
				return true;
			}
		}
		return false;
	}

	protected void doTransition(NodeInputParam input) {
		if (checkIndex(lastSelectedIndex)) {
			children.get(lastSelectedIndex).transition(input);
		}
		lastSelectedIndex = -1;
	}

	protected int doTick(NodeInputParam input) {
		int isFinish = BRS_FINISH;

		if (checkIndex(currentSelectedIndex)) {
			if (currentSelectedIndex != lastSelectedIndex) {
				if (checkIndex(lastSelectedIndex)) {
					children.get(lastSelectedIndex).transition(input);
				}

				lastSelectedIndex = currentSelectedIndex;
			}
		}

		if (checkIndex(lastSelectedIndex)) {
			isFinish = children.get(lastSelectedIndex).tick(input);
			if (isFinish == BRS_FINISH)
				lastSelectedIndex = -1;
		}

		return isFinish;
	}

	protected int currentSelectedIndex = -1;
	protected int lastSelectedIndex = -1;
}
