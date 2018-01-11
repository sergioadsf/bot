package br.com.sergio.bot.action.football;

import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.command.AbsCommand;
import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.model.ParamCMD;
import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.model.football.TipoRodada;
import br.com.sergio.bot.util.MarkdownWriter;

public class RoundAction extends AbsFootballAction {

	public void action(AbsSender absSender, MarkdownWriter msg, String text)
			throws AnswerException, Exception {
		TipoRodada tipoRodada = isRound(text);
		if (tipoRodada != null && AbsCommand.next.containsKey(msg.getUserId())) {
			ParamCMD paramCMD = AbsCommand.next.get(msg.getUserId());
			FootSearch footSearch = (FootSearch) paramCMD.getParam();
			footSearch.setRound(tipoRodada.getValue());
			getiBotFootballService().findRound(absSender, msg, footSearch);
			return;
		}
	}
	
	private TipoRodada isRound(String text) {
		return TipoRodada.contains(text);
	}

}
