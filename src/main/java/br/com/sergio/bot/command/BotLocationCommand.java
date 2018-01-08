package br.com.sergio.bot.command;

import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

public abstract class BotLocationCommand implements BotCommand {

	@Override
	public void execute(AbsSender absSender, Message message) throws Exception {
		throw new Exception("não permitido");
	}

	public abstract void execute(AbsSender absSender, Message message, Location location) throws Exception;
}
