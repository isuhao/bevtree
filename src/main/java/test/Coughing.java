package test;

import ai.core.NodeInputParam;
import ai.core.NodeTerminal;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Coughing")
public class Coughing extends NodeTerminal {
	public Coughing(String debugName) {
		super(debugName);
	}

	protected void doEnter(NodeInputParam input) {
	}

	protected int doExecute(NodeInputParam input) {

		System.out.println("咳嗽");
		return BRS_FINISH;
	}

}