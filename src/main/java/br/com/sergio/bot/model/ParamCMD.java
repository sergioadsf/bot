package br.com.sergio.bot.model;

import br.com.sergio.bot.command.AbsBotCommand;
import br.com.sergio.bot.command.CmdParam;

public class ParamCMD<T extends Object> {

	private CmdParam cmdParam;
	private AbsBotCommand cmd;
	private T param;

	public ParamCMD(AbsBotCommand cmd, T param) {
		super();
		this.cmd = cmd;
		this.param = param;
	}

	public ParamCMD(CmdParam cmdParam) {
		super();
		this.cmdParam = cmdParam;
	}

	public CmdParam getCmdParam() {
		return cmdParam;
	}

	public void setCmdParam(CmdParam cmdParam) {
		this.cmdParam = cmdParam;
	}

	public void setCmd(AbsBotCommand cmd) {
		this.cmd = cmd;
	}

	public void setParam(T param) {
		this.param = param;
	}

	public AbsBotCommand getCmd() {
		return cmd;
	}

	public T getParam() {
		return param;
	}

}
