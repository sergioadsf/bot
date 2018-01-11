package br.com.sergio.bot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.service.weather.IBotWeatherService;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class LocationCommand extends BotLocationCommand {

	@Autowired
	private IBotWeatherService iBotWeatherService;

	public AbsAction execute(AbsSender absSender, Message message, Location location) throws Exception {

		try {

			final Chat chat = message.getChat();
			User user = message.getFrom();
			MarkdownWriter msg = MarkdownWriter.start(chat.getId()).name(user.getFirstName(), user.getLastName()).userId(user.getId()).useName();
			iBotWeatherService.findCurrent(absSender, msg, location);

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

		return null;

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
