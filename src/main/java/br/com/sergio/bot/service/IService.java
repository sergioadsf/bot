package br.com.sergio.bot.service;

import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;

import br.com.sergio.bot.util.MarkdownWriter;

public interface IService {
	
	// TODO: Improve implementation of all submethods, for a while I am leaving this way. 
	boolean isKeyword(String text);
	
	default MarkdownWriter getMsgWriter(Message message) {
		final User user = message.getFrom();
		final Chat chat = message.getChat();
		String userName = user.getFirstName() + " " + user.getLastName();
		MarkdownWriter msg = MarkdownWriter.start(chat.getId()).bold(userName).newLine();
		return msg;
	}
	
}
