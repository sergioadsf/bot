package br.com.sergio.bot.service.football;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.service.IService;

public interface IBotFootballService extends IService {
	
	String[] keywords = new String[] { "campeonato", "resultado"};
	
	void findRound(AbsSender absSender, Message message) throws Exception;

}
