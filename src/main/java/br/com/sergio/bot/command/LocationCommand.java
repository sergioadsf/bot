package br.com.sergio.bot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.service.weather.IBotWeatherService;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class LocationCommand extends BotLocationCommand {

	@Autowired
	private IBotWeatherService iBotWeatherService;

	public AbsAction execute(AbsSender absSender, MarkdownWriter msg, Location location) throws Exception {

		try {

			msg.useName();
			iBotWeatherService.findCurrent(absSender, msg, location);

		} catch (Exception e1) {
			SendMessage answer = errorMessage(msg.getChatId(), e1);

			try {
				absSender.sendMessage(answer);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
		
		AbsCommand.next.remove(msg.getUserId());

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

	@Override
	AbsAction execute(AbsSender absSender, MarkdownWriter mw) throws Exception {
		return null;
	}


}
