package br.com.sergio.bot;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;

@Component
public class PrimeiroBot extends org.telegram.telegrambots.bots.TelegramWebhookBot {
	
	private Logger log = Logger.getLogger(PrimeiroBot.class);

	public String getBotPath() {
		log.info("PrimeiroBot.getBotPath()");
		return null;
	}

	public String getBotUsername() {
		return "SergioTest16Bot";
	}

	public BotApiMethod onWebhookUpdateReceived(Update arg0) {
		return null;
	}

	@Override
	public String getBotToken() {
		return System.getenv("TOKEN_BOT_SERGIO_TESTE_16");
	}
}
