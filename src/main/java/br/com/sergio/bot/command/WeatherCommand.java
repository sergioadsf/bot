package br.com.sergio.bot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.action.weather.WeatherAction;
import br.com.sergio.bot.util.KeyboardUtil;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class WeatherCommand extends AbsBotCommand {

	@Autowired
	private WeatherAction weatherAction;

	public static final String LOGTAG = "RESULTCOMMAND";

	public AbsAction execute(AbsSender absSender, MarkdownWriter msg) {

		msg.append("Obter clima atual? Ou gostaria de uma previsão para os próximos dias?");

		SendMessage answer = new SendMessage();
		answer.setChatId(msg.getChatId());
		answer.setReplyMarkup(KeyboardUtil.getListInlineKeyboard(msg.getUserId(), "pt", "Atual", "Previsão"));
		answer.setText(msg.get());
		answer.enableMarkdown(true);

		try {
			absSender.sendMessage(answer);
		} catch (TelegramApiException e) {
			BotLogger.error(LOGTAG, e);
			return null;
		}

		return weatherAction;

	}

}
