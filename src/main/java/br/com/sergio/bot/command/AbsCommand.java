package br.com.sergio.bot.command;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public class AbsCommand {

	@Autowired
	private StartCommand startCommand;

	@Autowired
	private ResultCommand resultCommand;

	@Autowired
	private AnalyzeCommand analyzeCommand;

	@Autowired
	private LocationCommand locationCommand;

	private Map<String, AbsBotCommand> commands = new LinkedHashMap<>();

	@PostConstruct
	public void init() {
		commands.put(CmdParam.START_CMD, startCommand);
		commands.put(CmdParam.RESULT_CMD, resultCommand);
		commands.put(CmdParam.ANALYZE_CMD, analyzeCommand);
		commands.put(CmdParam.LOCATION_CMD, locationCommand);
	}

	protected String getCommandText(Update update) {
		Message message = update.getMessage();
		String text = update.getCallbackQuery() == null ? message.getText() : update.getCallbackQuery().getData();
		if (text == null) {
			if (message.getLocation() != null) {
				text = CmdParam.LOCATION_CMD;
			}
		}
		return text;
	}

	protected AbsBotCommand getCommand(String cmd) {
		AbsBotCommand absBotCommand = commands.get(cmd);
		return absBotCommand == null ? analyzeCommand : absBotCommand;
	}

}
