package br.com.sergio.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.model.ParamCMD;
import br.com.sergio.bot.util.MarkdownWriter;
import br.com.sergio.bot.util.StringUtil;
import br.com.sergio.bot.util.TableUtil;

@Component
public class Command extends AbsCommand {

	public void execute(AbsSender sender, final Update update) {

		try {
			BotApiObject message = getMessage(update);
			String text = getCommandText(update);
			Integer userId = getUserId(update);
			AbsAction absActionNext = null;
			if (isCommand(text)) {
				next.put(userId, new ParamCMD<Object>(CmdParam.param(text)));
				absActionNext = this.getCommand(text).execute(sender, message);
				executeAction(userId, absActionNext);
			} else {
				AbsAction absAction = nextAction.get(userId);
				if (absAction != null) {
					absActionNext = absAction.execute(sender, message);
					executeAction(userId, absActionNext);
				} else {
					// MSG UNKNOWN
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void executeAction(Integer userId, AbsAction absActionNext) {
		if (absActionNext == null) {
			removeUser(userId);
		} else {
			nextAction.put(userId, absActionNext);
		}
	}

	private void removeUser(Integer userId) {
		next.remove(userId);
		nextAction.remove(userId);
	}

	public static boolean isCommand(String text) {
		return text.matches("/[a-z]{1,32}.*");
	}

	private BotApiObject getMessage(final Update update) {
		return update.getMessage() == null ? update.getCallbackQuery() : update.getMessage();
	}

	private Integer getUserId(Update update) {
		return update.getMessage() == null ? update.getCallbackQuery().getFrom().getId()
				: update.getMessage().getFrom().getId();
	}

}
