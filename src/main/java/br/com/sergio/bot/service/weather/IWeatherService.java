package br.com.sergio.bot.service.weather;

import br.com.sergio.bot.model.weather.CurrentForecast;

public interface IWeatherService {

	final String LOCATION = "location";
	final String ENTITY = "forecast";
	final String LINK = "cidade";

	CurrentForecast findCurrent(String cityName) throws Exception;

	CurrentForecast findCurrent(Float latitude, Float longitude) throws Exception;
}
