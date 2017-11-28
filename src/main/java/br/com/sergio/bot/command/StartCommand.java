package br.com.sergio.bot.command;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

public class StartCommand {

	public static final String LOGTAG = "STARTCOMMAND";

	public void execute(AbsSender absSender, User user, Chat chat) {
		StringBuilder messageBuilder = new StringBuilder();

		String userName = user.getFirstName() + " " + user.getLastName();

		messageBuilder.append("Hi ").append(userName).append("\n");
		messageBuilder.append("i think we know each other already!");

		SendMessage answer = new SendMessage();
		answer.setChatId(chat.getId().toString());
		answer.setText(messageBuilder.toString());

		try {
			absSender.sendMessage(answer);
		} catch (TelegramApiException e) {
			BotLogger.error(LOGTAG, e);
		}
	}

}
