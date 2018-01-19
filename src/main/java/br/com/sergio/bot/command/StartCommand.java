package br.com.sergio.bot.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class StartCommand extends AbsBotCommand {

	public static final String LOGTAG = "STARTCOMMAND";

	public AbsAction execute(AbsSender absSender, MarkdownWriter msg) {

		msg.append("Bem vindo, ").useName();
		msg.append("Como esta sendo seu dia hoje?");

		SendMessage answer = new SendMessage();
		answer.setChatId(msg.getChatId());
		answer.setText(msg.get());
		ReplyKeyboard replyMarkup = new InlineKeyboardMarkup();
		replyMarkup = getAlertsListKeyboard(msg.getUserId(), "pt");
		answer.setReplyMarkup(replyMarkup);

		try {
			absSender.sendMessage(answer);
			// addNextAction();
		} catch (TelegramApiException e) {
			BotLogger.error(LOGTAG, e);
		}

		return null;

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
		return "Cancel";// String.format(LocalisationUtil.getString("cancel", language),
						// EmojiUtil.CROSS_MARK.toString());
	}

}
