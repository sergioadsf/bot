package br.com.sergio.bot.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import br.com.sergio.bot.action.AbsAction;
import br.com.sergio.bot.model.ParamCMD;

@SuppressWarnings("rawtypes")
public class AbsCommand {
	
	public static Map<Integer, ParamCMD> next = new ConcurrentHashMap<>();
	
	protected static Map<Integer, AbsAction> nextAction = new ConcurrentHashMap<>();

	@Autowired
	private StartCommand startCommand;

	@Autowired
	private ResultCommand resultCommand;

	@Autowired
	private ClassificationCommand classificationCommand;

	@Autowired
	private AnalyzeCommand analyzeCommand;

	@Autowired
	private LocationCommand locationCommand;

	@Autowired
	private WeatherCommand weatherCommand;

	private Map<CmdParam, AbsBotCommand> commands = new LinkedHashMap<>();

	@PostConstruct
	public void init() {
		commands.put(CmdParam.START_CMD, startCommand);
		commands.put(CmdParam.RESULT_CMD, resultCommand);
		commands.put(CmdParam.CLASSIFICATION_CMD, classificationCommand);
//		commands.put(CmdParam.ANALYZE_CMD, analyzeCommand);
		commands.put(CmdParam.WEATHER_CMD, weatherCommand);
		commands.put(CmdParam.LOCATION_CMD, locationCommand);
		commands.put(CmdParam.CANCEL_CMD, analyzeCommand);
	}

	protected String getCommandText(Update update) {
		Message message = update.getMessage();
		String text = update.getCallbackQuery() == null ? message.getText() : update.getCallbackQuery().getData();
		if (text == null) {
			if (message.getLocation() != null) {
				text = CmdParam.LOCATION_CMD.getValue();
			}
		}
		return text;
	}

	protected AbsBotCommand getCommand(String cmd) {
		if(cmd.contains("@"))
			cmd = cmd.substring(0, cmd.indexOf("@"));
		
		AbsBotCommand absBotCommand = commands.get(CmdParam.param(cmd));
		return absBotCommand == null ? analyzeCommand : absBotCommand;
	}

}
