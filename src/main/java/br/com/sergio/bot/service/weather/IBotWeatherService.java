package br.com.sergio.bot.service.weather;

import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.service.IService;
import br.com.sergio.bot.util.MarkdownWriter;

public interface IBotWeatherService extends IService {
	
	void findCurrent(AbsSender absSender, MarkdownWriter msg, String text) throws Exception;

	void findCurrent(AbsSender absSender, MarkdownWriter msg, Location location) throws Exception;
	
	void forecast5Days(AbsSender absSender, MarkdownWriter msg, String text) throws Exception;

	void askCity(AbsSender absSender, MarkdownWriter msg);
}
