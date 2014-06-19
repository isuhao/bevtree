package test;

import ai.core.NodeInputParam;
import ai.core.NodeTerminal;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("Idle")
public class Idle extends NodeTerminal {
	public Idle(String debugName) {
		super(debugName);
	}

	protected void doEnter(NodeInputParam input) {
		waitTicks = 2;
	}

	protected int doExecute(NodeInputParam input) {
		if (--waitTicks > 0) {
			System.out.println(waitTicks + "  执行闲置");
			return BRS_EXECUTING;
		} else {
			System.out.println(waitTicks + "  闲置结束");
			return BRS_FINISH;
		}
	}

	@XStreamOmitField
	private int waitTicks;
}
