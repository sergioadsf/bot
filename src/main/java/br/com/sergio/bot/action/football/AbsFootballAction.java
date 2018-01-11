package br.com.sergio.bot.action.football;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.service.football.IBotFootballService;

public abstract class AbsFootballAction extends AbsAction {

	@Autowired
	private IBotFootballService iBotFootballService;

	public IBotFootballService getiBotFootballService() {
		return iBotFootballService;
	}

}
