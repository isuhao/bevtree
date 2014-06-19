package test;

import ai.core.NodeInputParam;
import ai.core.NodeTerminal;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("ToCD")
/**
 * 设置间隔时间
 *
 */
public class ToCD extends NodeTerminal {
	public ToCD(String debugName) {
		super(debugName);
	}

	public ToCD(String debugName, int cd) {
		super(debugName);
		this.cd = cd;
	}

	protected int doExecute(NodeInputParam input) {

		long currentTimeMillis = System.currentTimeMillis();
		if (curr == 0 || curr <= currentTimeMillis) {
			curr = currentTimeMillis + cd;
			System.out.println("cded");
			return BRS_FINISH;
		} else {
			System.out.println("cding");
			return BRS_EXECUTING;
		}

	}

	private int cd;
	@XStreamOmitField
	private long curr;
}