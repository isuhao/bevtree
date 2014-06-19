package ai.core;

import java.util.Vector;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 并行（Parallel）：将其所有子节点都运行一遍
 */
@XStreamAlias("NodeParallel")
public class NodeParallel extends Node {

	public static int CON_OR = 0;
	public static int CON_AND = 1;

	public NodeParallel(String debugName) {
		super(debugName);

		resetChildrenStatus();
	}

	public NodeParallel setFinishCondition(int condition) {
		finishCondition = condition;
		return this;
	}

	protected Boolean doEvaluate(NodeInputParam input) {
		int len = children.size();
		for (int i = 0; i < len; ++i) {
			if (childrenStatus.get(i) == BRS_EXECUTING) {
				if (!children.get(i).evaluate(input)) {
					return false;
				}
			}
		}

		return true;
	}

	protected int doTick(NodeInputParam input) {
		int i;
		int len = children.size();

		if (finishCondition == CON_OR) {
			for (i = 0; i < len; ++i) {
				if (childrenStatus.get(i) == BRS_EXECUTING)
					childrenStatus
							.set(i, children.get(i).tick(input));

				if (childrenStatus.get(i) != BRS_EXECUTING) {
					resetChildrenStatus();
					return BRS_FINISH;
				}

			}
		} else if (finishCondition == CON_AND) {
			int finishedCount = 0;

			for (i = 0; i < len; ++i) {
				if (childrenStatus.get(i) == BRS_EXECUTING)
					childrenStatus
							.set(i, children.get(i).tick(input));

				if (childrenStatus.get(i) != BRS_EXECUTING)
					++finishedCount;
			}

			if (finishedCount == len) {
				resetChildrenStatus();
				return BRS_FINISH;
			}
		} else {
			throw new Error("Unknown finish condition :" + finishCondition);
		}

		return BRS_EXECUTING;
	}

	protected void doTransition(NodeInputParam input) {
		resetChildrenStatus();

		int len = children.size();
		for (int i = 0; i < len; ++i)
			children.get(i).transition(input);
	}

	private void resetChildrenStatus() {
		for (int i = 0; i < MAX_CHILDREN; ++i) {
			childrenStatus.insertElementAt(BRS_EXECUTING, i);
		}
	}

	private int finishCondition;
	private Vector<Integer> childrenStatus = new Vector<Integer>(MAX_CHILDREN);
}
