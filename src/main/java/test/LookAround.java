package test;

import ai.core.NodeInputParam;
import ai.core.NodeTerminal;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("LookAround")
public class LookAround extends NodeTerminal {
	public LookAround(String debugName) {
		super(debugName);
	}

	protected void doEnter(NodeInputParam input) {
		waitTicks = 2;
	}

	protected int doExecute(NodeInputParam input) {

		if (--waitTicks > 0) {
			System.out.println("环顾四周");
			return BRS_EXECUTING;
		} else {
			System.out.println("结束环顾四周");
			return BRS_FINISH;
		}
	}

	private int waitTicks;
}