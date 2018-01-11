package br.com.sergio.bot.command;

import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.exception.AnswerException;
import br.com.sergio.bot.model.ParamCMD;
import br.com.sergio.bot.util.EmojiUtil;
import br.com.sergio.bot.util.MarkdownWriter;

public abstract class AbsBotAnalyzeCommand implements AbsBotCommand {

	public AbsAction execute(AbsSender absSender, BotApiObject botObj) throws Exception {
		Message message = null;
		boolean isCallback = true;
		String text = null;
		User user = null;
		if (botObj instanceof Message) {
			message = (Message) botObj;
			user = message.getFrom();
			isCallback = false;
			text = message.getText();
		} else {
			CallbackQuery callbackQuery = (CallbackQuery) botObj;
			user = callbackQuery.getFrom();
			message = callbackQuery.getMessage();
			text = callbackQuery.getData();
		}

		String userName = user.getFirstName() + " " + user.getLastName();
		final Chat chat = message.getChat();
		SendMessage answer = null;

		MarkdownWriter msg = MarkdownWriter.start(chat.getId()).bold(userName).newLine();
		msg.userId(user.getId());

		if (AbsCommand.next.containsKey(user.getId())) {
			ParamCMD paramCMD = AbsCommand.next.get(user.getId());
			AbsBotAnalyzeCommand absBotAnalyzeCommand = (AbsBotAnalyzeCommand) paramCMD.getCmd();
			if (isCallback) {
				absBotAnalyzeCommand.executeCallback(absSender, user, msg, text);
			} else {
				absBotAnalyzeCommand.executeMessage(absSender, msg, text);
			}
			return null;
		}

		try {
			if (isCallback) {
				executeCallback(absSender, user, msg, text);
			} else {
				executeMessage(absSender, msg, text);
			}
		} catch (AnswerException e) {

			answer = sorryMessage(msg, msg.getChatId());
			send(absSender, answer);
		} catch (Exception e1) {

			answer = errorMessage(msg.getChatId(), e1);
			send(absSender, answer);
		}
		
		return null;

	}

	private void send(AbsSender absSender, SendMessage answer) {
		try {
			absSender.sendMessage(answer);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	abstract void executeCallback(AbsSender absSender, User user, MarkdownWriter msg, String text)
			throws AnswerException, Exception;

	abstract void executeMessage(AbsSender absSender, MarkdownWriter msg, String text)
			throws AnswerException, Exception;

	public SendMessage errorMessage(Long chatId, Exception e1) {
		SendMessage answer;
		answer = new SendMessage();
		answer.setChatId(chatId.toString());
		answer.setText(e1.getMessage());
		answer.enableMarkdown(true);
		return answer;
	}

	public SendMessage sorryMessage(MarkdownWriter msg, Long chatId) {
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

}
