package br.com.sergio.bot.service;

import br.com.sergio.bot.model.CurrentForecast;

public interface IWeatherService {
	
	final String ENTITY = "forecast";
	final String LINK = "cidade";

	CurrentForecast findCurrent(String cityName) throws Exception;
}
