package br.com.sergio.bot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.action.football.CompetitionAction;
import br.com.sergio.bot.model.football.TipoCampeonato;
import br.com.sergio.bot.util.KeyboardUtil;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class ClassificationCommand extends AbsBotCommand {

	@Autowired
	private CompetitionAction competitionAction;

	public static final String LOGTAG = "RESULTCOMMAND";

	public AbsAction execute(AbsSender absSender, MarkdownWriter msg) {

		msg.append("Bem vindo, ").useName().newLine();
		msg.append("Sobre qual campeonato você gostaria de saber?");

		SendMessage answer = new SendMessage();
		answer.setChatId(msg.getChatId());
		answer.setText(msg.get());
		ReplyKeyboard replyMarkup = KeyboardUtil.getListInlineKeyboard(msg.getUserId(), "pt", TipoCampeonato.names());
		answer.setReplyMarkup(replyMarkup);
		answer.enableMarkdown(true);

		try {
			absSender.sendMessage(answer);
		} catch (TelegramApiException e) {
			BotLogger.error(LOGTAG, e);
			return null;
		}

		return competitionAction;

	}

}
