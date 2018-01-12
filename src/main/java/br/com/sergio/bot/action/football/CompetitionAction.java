package br.com.sergio.bot.action.football;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.command.AbsCommand;
import br.com.sergio.bot.command.CmdParam;
import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.model.ParamCMD;
import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.model.football.TipoCampeonato;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class CompetitionAction extends AbsFootballAction {
	
	@Autowired
	private RoundAction roundAction;

	@SuppressWarnings("unchecked")
	public AbsAction action(AbsSender absSender, MarkdownWriter msg, String text) throws AnswerException, Exception {
		Integer userId = msg.getUserId();
		TipoCampeonato tipoCampeonato = isResult(text);
		ParamCMD<FootSearch> param = AbsCommand.next.get(userId);
		if (param.getCmdParam() == CmdParam.RESULT_CMD) {
			param.setParam(new FootSearch(tipoCampeonato.getValue()));
			getIBotFootballService().askRound(absSender, msg);
			return roundAction;
		} else {
			getIBotFootballService().findClassification(absSender, msg, tipoCampeonato.getValue());
			return this;
		}
	}

	private TipoCampeonato isResult(String text) {
		return TipoCampeonato.contains(text);
	}

}
