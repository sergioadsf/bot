package br.com.sergio.bot.command;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendLocation;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.model.football.TipoCampeonato;
import br.com.sergio.bot.service.football.IBotFootballService;
import br.com.sergio.bot.service.weather.IBotWeatherService;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class AnalyzeCommand extends AbsBotAnalyzeCommand {

	@Autowired
	private IBotWeatherService iBotWeatherService;

	@Autowired
	private IBotFootballService iBotFootballService;

	@Override
	void executeCallback(AbsSender absSender, Message message, MarkdownWriter msg, String text) throws AnswerException, Exception {

		TipoCampeonato tipo = isResult(text);
		if (tipo != null) {
			iBotFootballService.askRound(absSender, message, msg, tipo);
			return;
		}

		if (isCancel(text)) {
			cancelMessage(absSender, message, msg.getId());
			return;
		}
		
		throw new AnswerException("");
	}

	@Override
	void executeMessage(AbsSender absSender, Message message, MarkdownWriter msg, String text) throws AnswerException, Exception {
		Long chatId = msg.getId();
		if (isAnswerForecast(text)) {
			iBotWeatherService.findCurrent(absSender, message);
			return;
		}

		if (isFootball(text)) {
			iBotFootballService.findRound(absSender, message);
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
		
		throw new AnswerException("");
	}
	
	@Override
	public void execute(AbsSender absSender, Message message) throws Exception {
		throw new Exception("Not allowed!");
	}


	private TipoCampeonato isResult(String text) {
		return TipoCampeonato.contains(text);
	}

	private void cancelMessage(AbsSender absSender, Message message, Long chatId) throws TelegramApiException {
		MarkdownWriter msg = MarkdownWriter.start();
		msg.append("Gostaria de alguma outra informação? ").newLine();
		SendMessage answer = new SendMessage();
		answer.setChatId(chatId.toString());
		answer.setText(msg.get());
		answer.enableMarkdown(true);
		ReplyKeyboardRemove replyKeyboard = new ReplyKeyboardRemove();
		answer.setReplyMarkup(replyKeyboard);
		// absSender.forwardMessage(new ForwardMessage(chatId, chatId,
		// message.getMessageId()));
		absSender.sendMessage(answer);
	}

	private boolean isCancel(String text) {
		return text.toLowerCase().contains("cancelar");
	}

	private boolean isAnswerLocation(String text) {
		return text.contains("localizacao");
	}

	private boolean isAnswerForecast(String text) {
		return iBotWeatherService.isKeyword(text);
	}

	private boolean isFootball(String text) {
		return iBotFootballService.isKeyword(text);
	}

	private boolean isAnswerSticker(String text) {
		return text.contains("imagem");
	}

}
