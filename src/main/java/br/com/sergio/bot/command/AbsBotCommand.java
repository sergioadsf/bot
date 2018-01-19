package br.com.sergio.bot.command;

import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.util.MarkdownWriter;

public abstract class AbsBotCommand {

	AbsAction execute(AbsSender absSender, BotApiObject botObj) throws Exception {
		MarkdownWriter mw = MarkdownWriter.start();
		User user = null;
		if (botObj instanceof Message) {
			Message m = (Message) botObj; 
			user = m.getFrom();
			mw.chatId(m.getChat().getId());
		} else {
			CallbackQuery m = (CallbackQuery) botObj; 
			user = m.getFrom();
			mw.chatId(m.getMessage().getChatId());
		}
		
		mw.userId(user.getId()).name(user.getFirstName(), user.getLastName());
		return execute(absSender, mw);
	}

	abstract AbsAction execute(AbsSender absSender, MarkdownWriter mw) throws Exception;


}
