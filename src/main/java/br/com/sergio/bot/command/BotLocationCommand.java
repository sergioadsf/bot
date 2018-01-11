package br.com.sergio.bot.command;

import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;

public abstract class BotLocationCommand implements AbsBotCommand {

	@Override
	public AbsAction execute(AbsSender absSender, Message message) throws Exception {
		return execute(absSender, message, message.getLocation());
	}

	public abstract AbsAction execute(AbsSender absSender, Message message, Location location) throws Exception;
}
