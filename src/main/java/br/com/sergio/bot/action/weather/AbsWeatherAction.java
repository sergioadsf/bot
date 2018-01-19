package br.com.sergio.bot.action.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.service.weather.IBotWeatherService;
import br.com.sergio.bot.util.MarkdownWriter;

public abstract class AbsWeatherAction extends AbsAction {

	@Autowired
	private IBotWeatherService iBotWeatherService;

	@Autowired
	private WeatherCurrentAction currentAction;

	@Autowired
	private WeatherForecast5Action forecast5Action;

	@Autowired
	private WeatherAction weatherAction;
	
	protected boolean isBack(String text) {
		return text.contains("voltar");
	}

	protected void askCity(AbsSender absSender, MarkdownWriter msg) {
		getIBotWeatherService().askCity(absSender, msg);
	}

	protected IBotWeatherService getIBotWeatherService() {
		return iBotWeatherService;
	}

	protected WeatherCurrentAction getCurrentAction() {
		return currentAction;
	}

	protected WeatherForecast5Action getForecast5Action() {
		return forecast5Action;
	}

	public WeatherAction getWeatherAction() {
		return weatherAction;
	}

}
