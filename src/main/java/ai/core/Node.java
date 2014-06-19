package ai.core;
import java.util.Vector;

public class Node {

	public static int BRS_EXECUTING = 0;
	public static int BRS_FINISH = 1;
	public static int BRS_ERROR_TRANSITION = -1;

	public static int MAX_CHILDREN = 16;

	protected String debugName;
	protected NodePreCondition precondition;
	protected Vector<Node> children;
	protected Node parent;

	public String debugName() {
		return this.debugName;
	}

	public  Node(String debugName) {
		this.debugName = debugName;
	}

	final public Node addChild(Node node) {
		if (children == null)
			children = new Vector<Node>();

		if (children.size() == MAX_CHILDREN)
			throw new Error(this + " overflow, max children number is "
					+ MAX_CHILDREN);

		children.add(node);
		node.parent = this;
		return this;
	}

	public Node addChildAt(Node node, int index) {
		this.addChild(node);

		if (index < 0)
			index = 0;
		else if (index > children.size() - 1)
			index = children.size();

		for (int i = children.size() - 1; i > index; --i) {
			children.set(i, children.get(i - 1));
		}

		children.set(index, node);

		return this;
	}

	final public Node setPrecondition(NodePreCondition precondition) {
		this.precondition = precondition;
		return this;
	}

	final public Boolean evaluate(NodeInputParam input) {
		Boolean ret = precondition==null || precondition.evaluate(input);
		return ret && doEvaluate(input);
	}

	final public void transition(NodeInputParam input) {
		doTransition(input);
	}

	final public int tick(NodeInputParam input) {
		return doTick(input);
	}

	protected Boolean doEvaluate(NodeInputParam input) {
		return true;
	}

	protected void doTransition(NodeInputParam input) {
		// nothing to do ... implement yourself
	}

	protected int doTick(NodeInputParam input) {
		return BRS_FINISH;
	}

	final protected Boolean checkIndex(int i) {
		return i > -1 && i < children.size();
	}

}
