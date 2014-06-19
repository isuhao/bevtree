package test;

import ai.core.NodeInputParam;
import ai.core.NodeTerminal;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("MoveTo")
public class MoveTo extends NodeTerminal {
	public MoveTo(String debugName) {
		super(debugName);
	}

	@Override
	protected void doEnter(NodeInputParam input) {
		waitTicks = 2;
	}

	protected int doExecute(NodeInputParam input) {

		if (--waitTicks > 0) {
			System.out.println("移动");
			return BRS_EXECUTING;
		} else {
			System.out.println("移动结束");
			return BRS_FINISH;
		}
	}

	private int waitTicks;
}