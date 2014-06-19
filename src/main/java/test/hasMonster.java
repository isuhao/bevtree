package test;

import java.util.Random;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import ai.core.NodeInputParam;
import ai.core.NodePreCondition;

@XStreamAlias("hasMonster")
public class hasMonster extends NodePreCondition {
	public final static Integer code = 200;
	public Boolean evaluate(NodeInputParam input) {
		Random rd = new Random();
		Boolean check = rd.nextBoolean();
		if (check) {
			System.out.println("发现怪物");
		} else {
			System.out.println("没发现怪物");
		}
		return check;
	}
}