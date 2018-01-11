package br.com.sergio.bot.model;

import br.com.sergio.bot.action.AbsAction;

public class ParamAction<T extends Object> {

	private AbsAction action;
	private T param;

	public ParamAction(AbsAction action) {
		super();
		this.action = action;
	}

	public ParamAction(AbsAction action, T param) {
		super();
		this.action = action;
		this.param = param;
	}

	public AbsAction getAction() {
		return action;
	}

	public T getParam() {
		return param;
	}

}
