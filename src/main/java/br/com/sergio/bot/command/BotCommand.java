package br.com.sergio.bot.command;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

public interface BotCommand {

	void execute(AbsSender absSender, Message message);

}
