package br.com.sergio.bot.action.football;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.command.AbsCommand;
import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.model.ParamCMD;
import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.model.football.TipoRodada;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class RoundAction extends AbsFootballAction {

	public AbsAction action(AbsSender absSender, MarkdownWriter msg, String text) throws AnswerException, Exception {
		TipoRodada tipoRodada = isRound(text);
		Integer value = tipoRodada == null ? Integer.valueOf(text) : tipoRodada.getValue();
		ParamCMD paramCMD = AbsCommand.next.get(msg.getUserId());
		FootSearch footSearch = (FootSearch) paramCMD.getParam();
		footSearch.setRound(value);
		getIBotFootballService().findRound(absSender, msg, footSearch);
		return this;
	}

	private TipoRodada isRound(String text) {
		return TipoRodada.contains(text);
	}

}
