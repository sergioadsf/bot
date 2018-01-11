package br.com.sergio.bot.action.football;

import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.command.AbsCommand;
import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.model.ParamAction;
import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.model.football.TipoCampeonato;
import br.com.sergio.bot.util.MarkdownWriter;

public class CompetitionAction extends AbsFootballAction {

	public void action(AbsSender absSender, MarkdownWriter msg, String text)
			throws AnswerException, Exception {
		TipoCampeonato tipoCampeonato = isResult(text);
		if (tipoCampeonato != null) {
			AbsCommand.nextAction.put(msg.getUserId(), new ParamAction<FootSearch>(this, new FootSearch(tipoCampeonato.getValue())));
			getiBotFootballService().askRound(absSender, msg);
			return;
		}
	}
	
	private TipoCampeonato isResult(String text) {
		return TipoCampeonato.contains(text);
	}

}
