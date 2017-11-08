package br.com.sergio.bot;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Update;

@Component
public class SegundoBot extends org.telegram.telegrambots.bots.TelegramLongPollingBot {

	private Logger log = Logger.getLogger(SegundoBot.class);

	public String getBotUsername() {
		return "SergioTest16Bot";
	}

	@Override
	public String getBotToken() {
		return System.getenv("TOKEN_BOT_SERGIO_TESTE_16");
	}

	public void onUpdateReceived(Update arg0) {
		log.info("SegundoBot.onUpdateReceived()");
	}
}
