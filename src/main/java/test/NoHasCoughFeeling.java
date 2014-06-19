package test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import ai.core.NodeInputParam;
import ai.core.NodePreCondition;

@XStreamAlias("NoHasCoughFeeling")
public class NoHasCoughFeeling extends NodePreCondition {
	public Boolean evaluate(NodeInputParam input) {
		boolean b = Math.random() > 0.1;
		if (b) {
			System.out.println("没有咳的感觉");
		} else {
			System.out.println("有咳的感觉");
		}
		return b;
	}
}