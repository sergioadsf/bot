package br.com.sergio.bot.action.weather;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class WeatherForecast5Action extends AbsWeatherAction {

	public AbsAction action(AbsSender absSender, MarkdownWriter msg, String text) throws AnswerException, Exception {
		if (isBack(text)) {
			return null;
		}

		getIBotWeatherService().forecast5Days(absSender, msg, text);
		return this;
	}
}
