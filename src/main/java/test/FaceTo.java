package test;

import ai.core.NodeInputParam;
import ai.core.NodeTerminal;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("FaceTo")
public class FaceTo extends NodeTerminal {

	public FaceTo(String debugName) {
		super(debugName);
	}

	protected int doExecute(NodeInputParam input) {

		Monster monster = input.getMonster();
		System.out.println(monster.getName() + "面对方向" + monster.getTarget());

		return BRS_FINISH;
	}
}