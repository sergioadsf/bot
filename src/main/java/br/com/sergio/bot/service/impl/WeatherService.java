package br.com.sergio.bot.service.impl;

import org.springframework.stereotype.Service;

import br.com.sergio.bot.model.CurrentForecast;
import br.com.sergio.bot.service.IWeatherService;

@Service
public class WeatherService extends AbsService implements IWeatherService {

	@Override
	public CurrentForecast findCurrent(String cityName) throws Exception {
		String url = getBotConfig().getWsUrl() + "/" + ENTITY + "/" + LINK + "/";
		String json = this.getConnectService().get(url, cityName);
		return strToObj(json, CurrentForecast.class);
	}

}
