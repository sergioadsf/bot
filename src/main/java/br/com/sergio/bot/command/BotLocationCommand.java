package br.com.sergio.bot.command;

import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.util.MarkdownWriter;

public abstract class BotLocationCommand extends AbsBotCommand {

	AbsAction execute(AbsSender absSender, BotApiObject botObj) throws Exception {
		MarkdownWriter mw = MarkdownWriter.start();
		User user = null;
		Location location = null;
		if (botObj instanceof Message) {
			Message m = (Message) botObj;
			location = m.getLocation();
			user = m.getFrom();
			mw.chatId(m.getChat().getId());
		} else {
			CallbackQuery m = (CallbackQuery) botObj;
			user = m.getFrom();
			Message message = m.getMessage();
			location = message.getLocation();
			mw.chatId(message.getChatId());
		}

		mw.userId(user.getId()).name(user.getFirstName(), user.getLastName());
		
		return execute(absSender, mw, location);
	}

	public abstract AbsAction execute(AbsSender absSender, MarkdownWriter mw, Location location) throws Exception;
}
