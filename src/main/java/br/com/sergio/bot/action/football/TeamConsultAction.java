package br.com.sergio.bot.action.football;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.command.AbsCommand;
import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.model.ParamCMD;
import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class TeamConsultAction extends AbsFootballAction {

	@SuppressWarnings("unchecked")
	public AbsAction action(AbsSender absSender, MarkdownWriter msg, String team) throws AnswerException, Exception {
		Integer userId = msg.getUserId();
		ParamCMD<FootSearch> param = AbsCommand.next.get(userId);
		FootSearch fs = param.getParam();
		fs.setTeam(team);
		getIBotFootballService().findTeam(absSender, msg, fs);
		return this;
	}

	public AbsAction askTeam(AbsSender absSender, MarkdownWriter msg) {
		return this;
	}

}
