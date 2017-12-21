package br.com.sergio.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;

@Component
public class Command {

	public void execute(AbsSender sender, final Update update) {

		Message message = update.getMessage();
		switch (message.getText()) {
		case CmdParam.START_CMD:
			StartCommand.getInstance().execute(sender, message);
			break;

		default:
			
			AnalyzeCommand.getInstance().execute(sender, message);
			break;
		}

	}

}
