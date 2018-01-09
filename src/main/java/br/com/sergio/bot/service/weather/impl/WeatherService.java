package br.com.sergio.bot.service.weather.impl;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.sergio.bot.exception.NotFoundException;
import br.com.sergio.bot.model.weather.CurrentForecast;
import br.com.sergio.bot.model.weather.Response;
import br.com.sergio.bot.service.impl.AbsService;
import br.com.sergio.bot.service.weather.IWeatherService;

@Service
public class WeatherService extends AbsService implements IWeatherService {

	@Override
	public CurrentForecast findCurrent(String cityName) throws Exception {
		String url = getBotConfig().getWsUrlWeather() + "/" + ENTITY + "/" + LINK + "/";
		String json = this.getConnectService().get(url, cityName);
		Response<CurrentForecast> response = strToObj(json, new TypeReference<Response<CurrentForecast>>() {
		});

		if (!response.isSuccess()) {
			throw new NotFoundException();
		}

		return response.getResponse();
	}

	@Override
	public CurrentForecast findCurrent(Float latitude, Float longitude) throws Exception {
		String url = getBotConfig().getWsUrlWeather() + "/" + ENTITY + "/" + LOCATION + "/";
		String json = this.getConnectService().post(url, latitude + "," + longitude);
		Response<CurrentForecast> response = strToObj(json, new TypeReference<Response<CurrentForecast>>() {
		});

		if (!response.isSuccess()) {
			throw new NotFoundException();
		}

		return response.getResponse();
	}

}
