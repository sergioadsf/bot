package br.com.sergio.bot;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Update;

@Component
public class PollingBot extends org.telegram.telegrambots.bots.TelegramLongPollingBot {

	private Logger log = Logger.getLogger(PollingBot.class);

	public String getBotUsername() {
		return BotConfig.USER;
	}

	@Override
	public String getBotToken() {
		return BotConfig.getToken();
	}

	public void onUpdateReceived(Update arg0) {
		log.info("SegundoBot.onUpdateReceived()");
	}
}
