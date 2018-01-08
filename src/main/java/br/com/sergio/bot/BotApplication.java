package br.com.sergio.bot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@SpringBootApplication
@ComponentScan("br.com.sergio.bot")
@EnableScheduling
public class BotApplication {

	private Logger log = Logger.getLogger(BotApplication.class);

	private TelegramBotsApi telegramBotsApi;

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(BotApplication.class, args);
	}

	@Autowired
	private void init(WebhookBot bot, PollingBot bot2) {
		telegramBotsApi = new TelegramBotsApi();
		try {
			log.info("Iniciando Sergio Bot.");
//			telegramBotsApi.registerBot(bot);
			telegramBotsApi.registerBot(bot2);
			log.info("Sergio Bot iniciado.");
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
