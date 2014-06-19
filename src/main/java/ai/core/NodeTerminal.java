package ai.core;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;



/**
 * BevNodeTerminal
 * 
 */
@XStreamAlias("NodeTerminal")
public class NodeTerminal extends Node {
	
	public static int STA_READY = 0;
	public static int STA_RUNNING = 1;
	public static int STA_FINISH = 2;

	public NodeTerminal(String debugName) {
		super(debugName);
	}

	final protected int doTick(NodeInputParam input) {
		int isFinish = BRS_FINISH;

		if (status == STA_READY) {
			doEnter(input);
			needExit = true;
			status = STA_RUNNING;
		}

		if (status == STA_RUNNING) {
			isFinish = doExecute(input);
			if (isFinish == BRS_FINISH || isFinish < 0)
				status = STA_FINISH;
		}

		if (status == STA_FINISH) {
			if (needExit)
				doExit(input, isFinish);

			status = STA_READY;
			needExit = false;
		}

		return isFinish;
	}

	final protected void doTransition(NodeInputParam input) {
		if (needExit)
			doExit(input, BRS_ERROR_TRANSITION);

		status = STA_READY;
		needExit = false;
	}

	protected void doEnter(NodeInputParam input) {
		// nothing to do...implement yourself
	}

	protected int doExecute(NodeInputParam input) {
		return BRS_FINISH;
	}

	protected void doExit(NodeInputParam input, int exitID) {
		// nothing to do...implement yourself
	}

	@XStreamOmitField
	private int status = STA_READY;
	@XStreamOmitField
	private boolean needExit;
}