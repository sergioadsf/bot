package br.com.sergio.bot.command;

import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

public interface Command {

	void execute(final Update update);

	void execute(AbsSender absSender, User user, Chat chat);

}
