package test;

import ai.core.NodeInputParam;
import ai.core.NodeTerminal;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ToSkill")
public class ToSkill extends NodeTerminal {
	public ToSkill(String debugName) {
		super(debugName);
	}

	public ToSkill(String debugName, int skill) {
		super(debugName);
		this.skill = skill;
	}

	protected void doEnter(NodeInputParam input) {
//		skill = input.getMonster().getSkill();
	}

	protected int doExecute(NodeInputParam input) {

		if (input.getMonster().getSkill() == skill) {

			Monster monster = input.getMonster();
			Long cds = monster.getCds(skill);
			if (cds <= 0 || cds <= System.currentTimeMillis()) {

				System.out.println("施放技能" + skill);
				input.getMonster().setCds(skill,
						System.currentTimeMillis() + 3000);
				return BRS_FINISH;
			}
			System.out.println("技能冷却" + skill);
			return BRS_EXECUTING;

		}
		return BRS_FINISH;

	}

	private int skill;
}