package br.com.sergio.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;

@Component
public class Command extends AbsCommand {

	public void execute(AbsSender sender, final Update update) {

		try {
			BotApiObject message = getMessage(update);
			String text = getCommandText(update);
			this.getCommand(text).execute(sender, message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private BotApiObject getMessage(final Update update) {
		return update.getMessage() == null ? update.getCallbackQuery() : update.getMessage();
	}

}
