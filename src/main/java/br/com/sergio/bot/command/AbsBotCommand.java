package br.com.sergio.bot.command;

import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.model.ParamCMD;

public interface AbsBotCommand {

	default AbsAction execute(AbsSender absSender, BotApiObject botObj) throws Exception {
		Message message = (Message) botObj;
		User user = message.getFrom();
		if (AbsCommand.next.containsKey(user.getId())) {
			ParamCMD paramCMD = AbsCommand.next.get(user.getId());
			return paramCMD.getCmd().execute(absSender, message);
		}
		return execute(absSender, message);
	}

	AbsAction execute(AbsSender absSender, Message message) throws Exception;

}
