package test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import ai.core.NodeInputParam;
import ai.core.NodePreCondition;

@XStreamAlias("HasSound")
public class HasSound extends NodePreCondition {
	
	public Boolean evaluate(NodeInputParam input) {
		Monster monster = input.getMonster();

		boolean hasSound = monster.HasSound();
		if (hasSound) {
			input.setTarget(new Monster());
			monster.setTarget("东");
			System.out.println("有声音");
		} else {
			System.out.println("没声音");
		}
		return hasSound;
	}
}
