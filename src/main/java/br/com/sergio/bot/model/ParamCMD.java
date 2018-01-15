package br.com.sergio.bot.model;

import br.com.sergio.bot.command.AbsBotCommand;
import br.com.sergio.bot.command.CmdParam;

public class ParamCMD<T extends Object> {

	private CmdParam cmdParam;
	private T param;

	public ParamCMD(T param) {
		super();
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

	public void setParam(T param) {
		this.param = param;
	}

	public T getParam() {
		return param;
	}

}
