package br.com.sergio.bot.service.weather;

import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.service.IService;

public interface IBotWeatherService extends IService {
	
	void findCurrent(AbsSender absSender, Message message) throws Exception;

	void findCurrent(AbsSender absSender, Message message, Location location) throws Exception;
}
