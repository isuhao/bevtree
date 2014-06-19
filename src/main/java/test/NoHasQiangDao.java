package test;

import java.util.Random;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import ai.core.NodeInputParam;
import ai.core.NodePreCondition;

@XStreamAlias("NoHasQiangDao")
public class NoHasQiangDao extends NodePreCondition {
	public Boolean evaluate(NodeInputParam input) {
		boolean c = new Random().nextBoolean();
		if (c) {
			System.out.println("没强盗");
		}else {
			System.out.println("有强盗");			
		}
		return c;
	}
}