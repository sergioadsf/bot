package br.com.sergio.bot.action.football;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.service.football.IBotFootballService;

public abstract class AbsFootballAction extends AbsAction {
	
	protected final static String ALL 	= "todos";
	protected final static String GROUP = "grupo";
	protected final static String TEAM 	= "time";

	@Autowired
	private IBotFootballService iBotFootballService;

	public IBotFootballService getIBotFootballService() {
		return iBotFootballService;
	}

}
