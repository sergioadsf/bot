package br.com.sergio.bot.command;

import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

public interface AbsBotCommand {
	
	default void execute(AbsSender absSender, BotApiObject message) throws Exception {
		execute(absSender, (Message)message);
	}
	
	void execute(AbsSender absSender, Message message) throws Exception;

}
