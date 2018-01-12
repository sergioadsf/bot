package br.com.sergio.bot.action;

import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.util.MarkdownWriter;

public abstract class AbsAction {

	public AbsAction execute(AbsSender absSender, BotApiObject botObj) throws Exception {
		if (botObj instanceof Message) {
			return execute(absSender, (Message) botObj);
		} else {
			return execute(absSender, (CallbackQuery) botObj);
		}
	}

	public AbsAction execute(AbsSender absSender, CallbackQuery callObj) throws Exception {
		User user = callObj.getFrom();
		Message message = callObj.getMessage();
		String text = callObj.getData();

		MarkdownWriter msg = MarkdownWriter.start(message.getChatId()).userId(user.getId())
				.name(user.getFirstName(), user.getLastName()).useName();

		if (isCancel(text)) {
			cancelMessage(absSender, msg);
			return null;
		}
		
		return action(absSender, msg, text);
	}

	public AbsAction execute(AbsSender absSender, Message message) throws Exception {
		User user = message.getFrom();
		String text = message.getText();
		MarkdownWriter msg = MarkdownWriter.start(message.getChatId()).userId(user.getId())
				.name(user.getFirstName(), user.getLastName()).useName();
		return action(absSender, msg, text);
	}
	
	private boolean isCancel(String text) {
		return text.toLowerCase().contains("cancelar");
	}
	
	private void cancelMessage(AbsSender absSender, MarkdownWriter msg) throws TelegramApiException {
		msg.newLine().append("Gostaria de alguma outra informação? ").newLine();
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



	public abstract AbsAction action(AbsSender absSender, MarkdownWriter msg, String text)
			throws AnswerException, Exception;

}
