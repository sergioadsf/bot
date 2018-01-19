package br.com.sergio.bot.command;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.model.ParamCMD;
import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.model.football.TipoCampeonato;
import br.com.sergio.bot.model.football.TipoRodada;
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
	void executeCallback(AbsSender absSender, User user, MarkdownWriter msg, String text)
			throws AnswerException, Exception {

		// if (isCancel(text)) {
		// cancelMessage(absSender, msg);
		// AbsCommand.next.remove(msg.getUserId());
		// return;
		// }

		throw new AnswerException("");
	}

	@Override
	void executeMessage(AbsSender absSender, MarkdownWriter msg, String text) throws AnswerException, Exception {
		if (isAnswerForecast(text)) {
			iBotWeatherService.findCurrent(absSender, msg, text);
			return;
		}

		if (AbsCommand.next.containsKey(msg.getUserId())) {
			ParamCMD paramCMD = AbsCommand.next.get(msg.getUserId());
			FootSearch footSearch = (FootSearch) paramCMD.getParam();
			footSearch.setRound(Integer.valueOf(text));
			iBotFootballService.findRound(absSender, msg, footSearch);
			return;
		}

		if (isAnswerSticker(text)) {
			SendSticker s = new SendSticker();
			s.setChatId(msg.getChatId());
			s.setSticker("Smiling Face With Heart-Shaped Eye");
			File f = new File(AnalyzeCommand.class.getResource("/static/sticker/corleone/corleone-romantico.jfif")
					.toString().substring(6));
			s.setNewSticker(f);

			absSender.sendSticker(s);
			return;
		}

		throw new AnswerException("");
	}

	private TipoCampeonato isResult(String text) {
		return TipoCampeonato.contains(text);
	}

	private TipoRodada isRound(String text) {
		return TipoRodada.contains(text);
	}

	private void cancelMessage(AbsSender absSender, MarkdownWriter msg) throws TelegramApiException {
		msg.append("Gostaria de alguma outra informação? ").newLine();
		SendMessage answer = new SendMessage();
		answer.setChatId(msg.getChatId());
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

	@Override
	AbsAction execute(AbsSender absSender, MarkdownWriter mw) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
