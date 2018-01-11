package br.com.sergio.bot.action;

import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.util.MarkdownWriter;

public abstract class AbsAction {

	public void execute(AbsSender absSender, BotApiObject botObj) throws Exception {
		if (botObj instanceof Message) {
			execute(absSender, (Message) botObj);
		} else {
			execute(absSender, (CallbackQuery) botObj);
		}

		// String userName = user.getFirstName() + " " + user.getLastName();
		// final Chat chat = message.getChat();
		// SendMessage answer = null;
		//
		// MarkdownWriter msg =
		// MarkdownWriter.start(chat.getId()).bold(userName).newLine();
		// msg.userId(user.getId());
		//
		// if (AbsCommand.next.containsKey(user.getId())) {
		// ParamCMD paramCMD = AbsCommand.next.get(user.getId());
		// AbsBotAnalyzeCommand absBotAnalyzeCommand = (AbsBotAnalyzeCommand)
		// paramCMD.getCmd();
		// if (isCallback) {
		// absBotAnalyzeCommand.executeCallback(absSender, user, msg, text);
		// } else {
		// absBotAnalyzeCommand.executeMessage(absSender, msg, text);
		// }
		// return;
		// }

	}

	public void execute(AbsSender absSender, CallbackQuery callObj) throws Exception {
		User user = callObj.getFrom();
		Message message = callObj.getMessage();
		String text = callObj.getData();
	}

	public void execute(AbsSender absSender, Message message) throws Exception {
		User user = message.getFrom();
		String text = message.getText();
	}

	public abstract void action(AbsSender absSender, MarkdownWriter msg, String text) throws AnswerException, Exception;

}
