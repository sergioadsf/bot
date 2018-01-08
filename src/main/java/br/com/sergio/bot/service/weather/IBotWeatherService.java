package br.com.sergio.bot.service.weather;

import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

public interface IBotWeatherService {
	
	String[] keywords = new String[] { "cidade", "clima", "temperatura", "temp" };

	void findCurrent(AbsSender absSender, Message message) throws Exception;

	void findCurrent(AbsSender absSender, Message message, Location location) throws Exception;
}
