package br.com.sergio.bot.action.weather;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class WeatherAction extends AbsWeatherAction {

	private static final String ATUAL = "atual";

	public AbsAction action(AbsSender absSender, MarkdownWriter msg, String text) throws AnswerException, Exception {

		if (isBack(text)) {
			return null;
		}
		askCity(absSender, msg);
		if (text.toLowerCase().equals(ATUAL)) {
			return this.getCurrentAction();
		}

		return this.getForecast5Action();
	}
}
