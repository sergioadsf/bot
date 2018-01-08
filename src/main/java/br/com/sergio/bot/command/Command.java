package br.com.sergio.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;

@Component
public class Command extends AbsCommand {

	public void execute(AbsSender sender, final Update update) {

		Message message = update.getMessage();
		String text = message.getText();
		if (text != null) {
			switch (text) {
			case CmdParam.START_CMD:
				this.getStartCommand().execute(sender, message);
				break;

			default:

				this.getAnalyzeCommand().execute(sender, message);
				break;
			}
		} else if (message.getLocation() != null) {
			Location location = message.getLocation();
			try {
				this.getLocationCommand().execute(sender, message, location);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
