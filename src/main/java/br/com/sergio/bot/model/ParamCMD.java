package br.com.sergio.bot.model;

import br.com.sergio.bot.command.AbsBotCommand;

public class ParamCMD<T extends Object> {

	private AbsBotCommand cmd;
	private T param;

	public ParamCMD(AbsBotCommand cmd, T param) {
		super();
		this.cmd = cmd;
		this.param = param;
	}

	public AbsBotCommand getCmd() {
		return cmd;
	}

	public T getParam() {
		return param;
	}

}
