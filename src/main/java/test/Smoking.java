package test;

import ai.core.NodeInputParam;
import ai.core.NodeTerminal;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Smoking")
public class Smoking extends NodeTerminal {

	public Smoking(String debugName) {
		super(debugName);
	}

	protected int doExecute(NodeInputParam input) {
		System.out.println("抽烟");
		return BRS_EXECUTING;
	}
}