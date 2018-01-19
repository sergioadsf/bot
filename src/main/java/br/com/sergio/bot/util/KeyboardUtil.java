package br.com.sergio.bot.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

public class KeyboardUtil {

	public static ReplyKeyboardMarkup getCancelKeyboard(Integer userId, String language) {
		ReplyKeyboardMarkup replyKeyboardMarkup = null;

		List<KeyboardRow> keyboard = new ArrayList<>();
		KeyboardRow row = new KeyboardRow();
		row.add(getCancelCommand(language));
		keyboard.add(row);

		replyKeyboardMarkup = new ReplyKeyboardMarkup();
		replyKeyboardMarkup.setKeyboard(keyboard);
		replyKeyboardMarkup.setOneTimeKeyboard(true);

		return replyKeyboardMarkup;
	}

	public static ReplyKeyboardMarkup getListKeyboard(Integer userId, String language, String... names) {
		ReplyKeyboardMarkup replyKeyboardMarkup = null;

		List<KeyboardRow> keyboard = new ArrayList<>();
		for (String name : names) {
			KeyboardRow row = new KeyboardRow();
			row.add(name);
			keyboard.add(row);
		}
		KeyboardRow row = new KeyboardRow();
		row.add(getCancelCommand(language));
		keyboard.add(row);

		replyKeyboardMarkup = new ReplyKeyboardMarkup();
		replyKeyboardMarkup.setKeyboard(keyboard);
		replyKeyboardMarkup.setOneTimeKeyboard(true);

		return replyKeyboardMarkup;
	}

	public static InlineKeyboardMarkup getListInlineKeyboard(Integer userId, String language, String... names) {
		return getListInlineKeyboard(userId, language, true, names);
	}

	public static InlineKeyboardMarkup getListInlineKeyboard(Integer userId, String language, boolean isCancel,
			String... names) {
		int sizeTotal = names.length;
		InlineKeyboardMarkup replyKeyboardMarkup = null;
		List<List<InlineKeyboardButton>> listButtons = new ArrayList<>();
		List<InlineKeyboardButton> list;

		int pos = 0;
		while (pos < sizeTotal) {
			list = new ArrayList<>();
			for (int j = 0; j < 2; j++) {
				if (pos == sizeTotal)
					break;
				String value = names[pos++];
				list.add(new InlineKeyboardButton(value).setCallbackData(value.toLowerCase()));
			}
			listButtons.add(list);
		}

		if (isCancel) {
			String cancelCommand = getCancelCommand(language);
			listButtons.add(Arrays
					.asList(new InlineKeyboardButton(cancelCommand).setCallbackData(cancelCommand.toLowerCase())));
		}

		replyKeyboardMarkup = new InlineKeyboardMarkup();
		replyKeyboardMarkup.setKeyboard(listButtons);

		return replyKeyboardMarkup;
	}

	public static String getCancelCommand() {
		return String.format("%s Cancelar", EmojiUtil.CROSS_MARK.toString());
	}

	private static String getCancelCommand(String language) {
		return String.format("%s Cancelar", EmojiUtil.CROSS_MARK.toString());// String.format(LocalisationUtil.getString("cancel",
		// language),
		// EmojiUtil.CROSS_MARK.toString());
	}

	public static InlineKeyboardMarkup getInlineCancelBack(Integer userId, String... names) {
		return getListInlineKeyboard(userId, "pt", false,
				String.format("%s %s", EmojiUtil.BACK_WITH_LEFTWARDS_ARROW_ABOVE.toString(), "Voltar"),
				KeyboardUtil.getCancelCommand());
	}

}
