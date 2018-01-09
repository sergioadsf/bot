package br.com.sergio.bot.service.football;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

public interface IBotFootballService {
	
	void findRound(AbsSender absSender, Message message) throws Exception;

}
