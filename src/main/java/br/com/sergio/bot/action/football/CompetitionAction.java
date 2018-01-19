package br.com.sergio.bot.action.football;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
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

	@Autowired
	private AllConsultAction allConsultAction;

	@Autowired
	private GroupConsultAction groupConsultAction;

	@Autowired
	private TeamConsultAction teamConsultAction;

	public AbsAction action(AbsSender absSender, MarkdownWriter msg, String text) throws AnswerException, Exception {
		ParamCMD<FootSearch> param = getParam(msg);
		TipoCampeonato tipoCampeonato = isResult(text);
		if (param.getCmdParam() == CmdParam.RESULT_CMD) {
			putParam(tipoCampeonato, param);
			getIBotFootballService().askRound(absSender, msg);
			return roundAction;
		} else {
			return redirect(absSender, msg, param, tipoCampeonato, text);
			// getIBotFootballService().findClassification(absSender, msg,
			// tipoCampeonato.getValue());
			// getIBotFootballService().askGroup(absSender, msg, tipoCampeonato.getValue());
			// return this;
		}
	}

	private void putParam(TipoCampeonato tipoCampeonato, ParamCMD<FootSearch> param) {
		param.setParam(new FootSearch(tipoCampeonato.getValue()));
	}

	private AbsAction redirect(AbsSender absSender, MarkdownWriter msg, ParamCMD<FootSearch> param, TipoCampeonato tipoCampeonato,
			String text) throws AnswerException, Exception {
		if (ALL.equals(text)) {
			return allConsultAction.action(absSender, msg, text);
		} else if (GROUP.equals(text)) {
			return groupConsultAction.askGroup(absSender, msg);
		} else if (TEAM.equals(text)) {
			return teamConsultAction;
		}
		putParam(tipoCampeonato, param);
		getIBotFootballService().ask(absSender, msg, tipoCampeonato.getValue());
		return this;
	}

	private TipoCampeonato isResult(String text) {
		return TipoCampeonato.contains(text);
	}

}
