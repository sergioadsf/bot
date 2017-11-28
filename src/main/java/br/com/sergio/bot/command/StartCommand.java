package br.com.sergio.bot.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import br.com.sergio.bot.util.EmojiUtil;
import br.com.sergio.bot.util.LocalisationUtil;

public class StartCommand implements BotCommand {

	public static final String LOGTAG = "STARTCOMMAND";

	private static BotCommand instance = null;

	public void execute(AbsSender absSender, Message message) {
		User user = message.getFrom();
		Chat chat = message.getChat();
		StringBuilder messageBuilder = new StringBuilder();

		String userName = user.getFirstName() + " " + user.getLastName();

		messageBuilder.append("Bem vindo, ").append(userName).append("\n");
		messageBuilder.append("Como esta sendo seu dia hoje?");

		SendMessage answer = new SendMessage();
		answer.setChatId(chat.getId().toString());
		answer.setText(messageBuilder.toString());
		ReplyKeyboard replyMarkup = new InlineKeyboardMarkup();
		replyMarkup = getAlertsListKeyboard(message.getFrom().getId(), "pt");
		answer.setReplyMarkup(replyMarkup);

		try {
			absSender.sendMessage(answer);
		} catch (TelegramApiException e) {
			BotLogger.error(LOGTAG, e);
		}

	}

	public static BotCommand getInstance() {
		if (instance == null) {
			instance = new StartCommand();
		}
		return instance;
	}

	private static ReplyKeyboardMarkup getAlertsListKeyboard(Integer userId, String language) {
		ReplyKeyboardMarkup replyKeyboardMarkup = null;

		List<KeyboardRow> keyboard = new ArrayList<>();
		for (String alertCityName : Arrays.asList("Bom", "Ruim")) {
			KeyboardRow row = new KeyboardRow();
			row.add(alertCityName);
			keyboard.add(row);
		}
		KeyboardRow row = new KeyboardRow();
		row.add(getCancelCommand(language));
		keyboard.add(row);

		replyKeyboardMarkup = new ReplyKeyboardMarkup();
		replyKeyboardMarkup.setKeyboard(keyboard);

		return replyKeyboardMarkup;
	}

	private static String getCancelCommand(String language) {
		return String.format(LocalisationUtil.getString("cancel", language), EmojiUtil.CROSS_MARK.toString());
	}
}
