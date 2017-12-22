package br.com.sergio.bot.command;

import org.springframework.beans.factory.annotation.Autowired;

public class AbsCommand {

	@Autowired
	private StartCommand startCommand;

	@Autowired
	private AnalyzeCommand analyzeCommand;

	protected StartCommand getStartCommand() {
		return startCommand;
	}

	protected AnalyzeCommand getAnalyzeCommand() {
		return analyzeCommand;
	}

}
