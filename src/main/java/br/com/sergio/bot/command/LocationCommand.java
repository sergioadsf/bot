package br.com.sergio.bot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.service.weather.IBotWeatherService;

@Component
public class LocationCommand extends BotLocationCommand {

	public static final String LOGTAG = "STARTCOMMAND";

	@Autowired
	private IBotWeatherService iBotWeatherService;

	public void execute(AbsSender absSender, Message message, Location location) throws Exception {

		try {

			iBotWeatherService.findCurrent(absSender, message, location);

		} catch (Exception e1) {
			final Chat chat = message.getChat();
			Long chatId = chat.getId();
			SendMessage answer = errorMessage(chatId, e1);

			try {
				absSender.sendMessage(answer);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}

	}

	private SendMessage errorMessage(Long chatId, Exception e1) {
		SendMessage answer;
		answer = new SendMessage();
		answer.setChatId(chatId.toString());
		answer.setText(e1.getMessage());
		answer.enableMarkdown(true);
		return answer;
	}

}
