package ai.core;

import test.Monster;

/**
 * BevNodeInputParam
 * 
 * 这里是传入ai的数据(任何数据)
 * 
 */
public class NodeInputParam {
	private Monster source;

	private Monster target;

	public NodeInputParam() {
	}

	public NodeInputParam(Monster monster) {
		this.source = monster;
	}

	public Monster getMonster() {
		return source;
	}

	public Monster getTarget() {
		return target;
	}

	public void setTarget(Monster target) {
		this.target = target;
	}

}
