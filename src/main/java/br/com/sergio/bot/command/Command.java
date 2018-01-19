package br.com.sergio.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.model.ParamCMD;

@Component
public class Command extends AbsCommand {

	public void execute(AbsSender sender, final Update update) {

		// AnswerCallbackQuery a = new AnswerCallbackQuery();
		// a.setCallbackQueryId(callObj.getId());
		// a.setShowAlert(true);
		// a.setText("ok");
		// absSender.answerCallbackQuery(a);

		String text = getCommandText(update);
		if (isStart(text)) {
			return;
		}
		try {
			BotApiObject botApi = getMessage(update);
			Integer userId = getUserId(update);
			AbsAction absActionNext = null;
			if (isCommand(text)) {
				next.put(userId, new ParamCMD<Object>(CmdParam.param(text)));
				absActionNext = this.getCommand(text).execute(sender, botApi);
				executeAction(sender, botApi, userId, absActionNext, text);
			} else {
				AbsAction absAction = nextAction.get(userId);
				if (absAction != null) {
					absActionNext = absAction.execute(sender, botApi);
					executeAction(sender, botApi, userId, absActionNext, text);
				} else {
					// MSG UNKNOWN
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isStart(String text) {
		return text.contains(CmdParam.START_CMD.getValue());
	}

	private void executeAction(AbsSender sender, BotApiObject botApi, Integer userId, AbsAction absActionNext,
			String text) throws Exception {
		if (absActionNext == null) {
			removeUser(sender, botApi, userId, text);
		} else {
			nextAction.put(userId, absActionNext);
		}
	}

	private void removeUser(AbsSender sender, BotApiObject botApi, Integer userId, String text) throws Exception {
		nextAction.remove(userId);
		if (isCancel(text)) {
			next.remove(userId);
		} else {
			if (next.containsKey(userId)) {
				ParamCMD paramCMD = next.get(userId);
				AbsAction absActionNext = this.getCommand(paramCMD.getCmdParam().getValue()).execute(sender, botApi);
				executeAction(sender, botApi, userId, absActionNext, text);
			}
		}
	}

	private boolean isCancel(String text) {
		return text.contains("cancel");
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
