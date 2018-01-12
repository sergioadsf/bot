package br.com.sergio.bot.action.weather;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.service.weather.IBotWeatherService;

public abstract class AbsWeatherAction extends AbsAction {

	@Autowired
	private IBotWeatherService iBotWeatherService;

	public IBotWeatherService getIBotWeatherService() {
		return iBotWeatherService;
	}

}
