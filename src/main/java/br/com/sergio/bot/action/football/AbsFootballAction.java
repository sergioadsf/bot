package br.com.sergio.bot.action.football;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.command.AbsCommand;
import br.com.sergio.bot.model.ParamCMD;
import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.service.football.IBotFootballService;
import br.com.sergio.bot.util.MarkdownWriter;

public abstract class AbsFootballAction extends AbsAction {
	
	protected final static String ALL 	= "todos";
	protected final static String GROUP = "grupo";
	protected final static String TEAM 	= "time";

	@Autowired
	private IBotFootballService iBotFootballService;
	
	@SuppressWarnings("unchecked")
	protected ParamCMD<FootSearch> getParam(MarkdownWriter msg) {
		Integer userId = msg.getUserId();
		ParamCMD<FootSearch> param = AbsCommand.next.get(userId);
		return param;
	}


	public IBotFootballService getIBotFootballService() {
		return iBotFootballService;
	}

}
