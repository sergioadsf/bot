package br.com.sergio.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import br.com.sergio.bot.model.football.TipoCampeonato;
import br.com.sergio.bot.util.KeyboardUtil;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class ResultCommand implements AbsBotCommand {

	public static final String LOGTAG = "RESULTCOMMAND";

	public void execute(AbsSender absSender, Message message) {
		User user = message.getFrom();
		Chat chat = message.getChat();
		
		MarkdownWriter msg = MarkdownWriter.start();

		String userName = user.getFirstName() + " " + user.getLastName();

		msg.append("Bem vindo, ").bold(userName).newLine();
		msg.append("Sobre qual campeonato você gostaria de saber?");

		SendMessage answer = new SendMessage();
		answer.setChatId(chat.getId().toString());
		answer.setText(msg.get());
		ReplyKeyboard replyMarkup = KeyboardUtil.getListInlineKeyboard(message.getFrom().getId(), "pt", TipoCampeonato.names());
		answer.setReplyMarkup(replyMarkup);
		answer.enableMarkdown(true);

		try {
			absSender.sendMessage(answer);
		} catch (TelegramApiException e) {
			BotLogger.error(LOGTAG, e);
		}

	}

}
