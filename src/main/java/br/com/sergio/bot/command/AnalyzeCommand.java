package br.com.sergio.bot.command;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendLocation;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.service.weather.IBotWeatherService;
import br.com.sergio.bot.util.EmojiUtil;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class AnalyzeCommand implements BotCommand {

	public static final String LOGTAG = "STARTCOMMAND";

	@Autowired
	private IBotWeatherService iBotWeatherService;

	public void execute(AbsSender absSender, Message message) {
		final User user = message.getFrom();
		String userName = user.getFirstName() + " " + user.getLastName();
		MarkdownWriter msg = MarkdownWriter.start().bold(userName).newLine();
		final Chat chat = message.getChat();
		SendMessage answer = null;

		Long chatId = chat.getId();
		try {

			String text = message.getText().toLowerCase();
			if (isAnswerForecast(text)) {
				iBotWeatherService.findCurrent(absSender, message);
				return;
			}

			if (isAnswerSticker(text)) {
				SendSticker s = new SendSticker();
				s.setChatId(chatId);
				s.setSticker("Smiling Face With Heart-Shaped Eye");
				File f = new File(AnalyzeCommand.class.getResource("/static/sticker/corleone/corleone-romantico.jfif")
						.toString().substring(6));
				s.setNewSticker(f);

				absSender.sendSticker(s);
				return;
			}

			if (isAnswerLocation(text)) {
				SendLocation location = new SendLocation();
				location.setChatId(chatId);
				// -16.651556, -49.243090
				location.setLatitude(-16.651556f);
				location.setLongitude(-49.243090f);
				absSender.sendLocation(location);

				return;
			}

			answer = sorryMessage(msg, chatId);

		} catch (Exception e1) {

			answer = errorMessage(chatId, e1);
		}

		try {
			absSender.sendMessage(answer);
		} catch (TelegramApiException e) {
			e.printStackTrace();
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

	private SendMessage sorryMessage(MarkdownWriter msg, Long chatId) {
		msg.emoji(EmojiUtil.FLUSHED_FACE);
		msg.emoji(EmojiUtil.FLUSHED_FACE);
		msg.append(
				" eh eh então rs to até com vergonha, porém ainda não aprendi a responder sobre isso. Sou novo ainda com o tempo aprendo! ")
				.newLine();

		SendMessage answer = new SendMessage();
		answer.setChatId(chatId.toString());
		answer.setText(msg.get());
		answer.enableMarkdown(true);
		return answer;
	}

	private boolean isAnswerLocation(String text) {
		return text.contains("localizacao");
	}

	private boolean isAnswerForecast(String text) {
		return text.contains("clima") || text.contains("temp") || text.contains("temperatura");
	}

	private boolean isAnswerSticker(String text) {
		return text.contains("imagem");
	}

}
