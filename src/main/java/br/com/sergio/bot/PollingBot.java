package br.com.sergio.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Update;

import br.com.sergio.bot.command.Command;

@Component
public class PollingBot extends org.telegram.telegrambots.bots.TelegramLongPollingBot {
	
	@Autowired
	private Command cmd;

	public String getBotUsername() {
		return BotConfig.USER;
	}

	@Override
	public String getBotToken() {
		return BotConfig.getToken();
	}

	public void onUpdateReceived(final Update update) {
		cmd.execute(update);
	}
}
