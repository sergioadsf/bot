package br.com.sergio.bot.action.football;

import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.model.ParamCMD;
import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class GroupConsultAction extends AbsFootballAction {

	public AbsAction action(AbsSender absSender, MarkdownWriter msg, String group) throws AnswerException, Exception {
		ParamCMD<FootSearch> param = getParam(msg);
		FootSearch fs = param.getParam();
		fs.setGroup(WordUtils.capitalize(group));
		getIBotFootballService().findGroup(absSender, msg, fs);
		return this;
	}

	public AbsAction askGroup(AbsSender absSender, MarkdownWriter msg) throws AnswerException, Exception {
		ParamCMD<FootSearch> param = getParam(msg);
		FootSearch fs = param.getParam();
		getIBotFootballService().askGroup(absSender, msg, fs);
		return this;
	}

}
