package ai.core.ext;

import ai.core.NodeInputParam;
import ai.core.NodeSelector;


/**
 * 不带优先级的选择节点（Non-priority Selector）：这种选择节点的选择顺序是从上一个执行过的子节点开始选择
 * ，如果前提满足，则继续执行此节点，如果条件不满足，则从此节点开始，依次判断每一个子节点的前提
 * ，当找到一个满足条件的子节点后，则执行该节点。这种方式，是基于一种称之为
 * “持续性”的假设，因为在游戏中，一个行为一般不会在一帧里结束，而是会持续一段时间，所以有时为了优化的目的
 * ，我们可以优先判断上一个执行的节点，当其条件不满足时
 * ，再寻找下一个可执行的节点。这种寻找方式不存在哪个节点优先判断的问题，所以对于前提的设置的要求，就是要保证
 * “互斥”（Exclusion）。如果我们用上面第一张图来说明
 * ，如果我们把控制节点换成不带优先级的选择节点，可以看到，当a=3时，第二个子节点会被执行，下一次当a变成9时
 * ，由于不是从头依次判断前提的，所以，我们还是会选择第二个节点
 * ，而不是我们可能期望的第一个节点。正确的做法见下图，注意每一个子节点的前提是“互斥的”。所以对于不带优先级的选择节点
 * ，它子节点的排列顺序就不是那么重要了，可以任意排列。
 */
public class NodeNonePrioritySelector extends NodeSelector {

	public NodeNonePrioritySelector(String debugName) {
		super(debugName);
	}

	protected Boolean doEvaluate(NodeInputParam input) {
		if (checkIndex(currentSelectedIndex)) {
			if (children.get(currentSelectedIndex).evaluate(input)) {
				return true;
			}
		}

		return super.doEvaluate(input);
	}
}
