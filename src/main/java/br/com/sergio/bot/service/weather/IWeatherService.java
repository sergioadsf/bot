package br.com.sergio.bot.service.weather;

import br.com.sergio.bot.model.weather.CurrentForecast;
import br.com.sergio.bot.model.weather.Forecast;

public interface IWeatherService {

	final String LOCATION = "location";
	final String ENTITY = "forecast";
	final String ENTITY5 = "forecast5";
	final String LINK = "cidade";

	CurrentForecast findCurrent(String cityName) throws Exception;
	
	CurrentForecast findCurrent(Float latitude, Float longitude) throws Exception;
	
	Forecast forecast5Days(String cityName) throws Exception;
}
