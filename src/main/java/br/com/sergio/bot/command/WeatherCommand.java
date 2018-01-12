package br.com.sergio.bot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.action.football.CompetitionAction;
import br.com.sergio.bot.action.weather.WeatherAction;
import br.com.sergio.bot.model.football.TipoCampeonato;
import br.com.sergio.bot.util.KeyboardUtil;
import br.com.sergio.bot.util.MarkdownWriter;

@Component
public class WeatherCommand extends AbsBotCommand {
	
	@Autowired
	private WeatherAction weatherAction; 

	public static final String LOGTAG = "RESULTCOMMAND";

	public AbsAction execute(AbsSender absSender, Message message) {
		User user = message.getFrom();
		Chat chat = message.getChat();
		
		MarkdownWriter msg = MarkdownWriter.start(chat.getId()).userId(user.getId()).name(user.getFirstName(), user.getLastName());

//		msg.append("Bem vindo, ").useName().newLine();
		msg.append("Agora me diga a cidade?");

		SendMessage answer = new SendMessage();
		answer.setChatId(msg.getChatId());
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
