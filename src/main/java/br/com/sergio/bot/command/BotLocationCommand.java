package br.com.sergio.bot.command;

import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

public abstract class BotLocationCommand implements AbsBotCommand {

	@Override
	public void execute(AbsSender absSender, Message message) throws Exception {
		execute(absSender, message, message.getLocation());
	}

	public abstract void execute(AbsSender absSender, Message message, Location location) throws Exception;
}
