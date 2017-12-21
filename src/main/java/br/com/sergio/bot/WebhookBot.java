package br.com.sergio.bot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@Component
public class WebhookBot extends org.telegram.telegrambots.bots.TelegramWebhookBot {

	private Logger log = Logger.getLogger(WebhookBot.class);
	
	@Autowired
	private BotConfig botConfig;

	@Override
	public void setWebhook(String arg0, String arg1) throws TelegramApiRequestException {
		super.setWebhook(arg0, arg1);
	}

	public String getBotPath() {
		log.info("PrimeiroBot.getBotPath()");
		return botConfig.getUser();
	}

	public String getBotUsername() {
		return botConfig.getUser();
	}

	public BotApiMethod<?> onWebhookUpdateReceived(Update arg0) {
		return null;
	}

	@Override
	public String getBotToken() {
		return botConfig.getToken();
	}
}
