package test;

import ai.core.NodeInputParam;
import ai.core.NodeTerminal;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Hovering")
public class Hovering extends NodeTerminal {
	public Hovering(String debugName) {
		super(debugName);
	}

	protected void doEnter(NodeInputParam input) {
	}

	protected int doExecute(NodeInputParam input) {

		System.out.println("徘徊");

		return BRS_FINISH;
	}

}