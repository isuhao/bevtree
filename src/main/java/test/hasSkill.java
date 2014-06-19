package test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import ai.core.NodeInputParam;
import ai.core.NodePreCondition;

@XStreamAlias("hasSkill")
public class hasSkill extends NodePreCondition {
	private int skill = 1000;

	public Boolean evaluate(NodeInputParam input) {
		Monster monster = input.getMonster();
		Long cds = monster.getCds(skill);
		if (cds == 0 || cds <= System.currentTimeMillis()) {
			System.out.println("可以放技能:" + skill);
			return true;
		} else {
			System.out.println("不可以放技能:" + skill + " " + cds);
			return false;
		}
	}
}