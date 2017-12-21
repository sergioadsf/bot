package br.com.sergio.bot.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sergio.bot.BotConfig;

public class BotController {

	@Autowired
	private BotConfig botConfig;

	protected BotConfig getBotConfig() {
		return this.botConfig;
	}
}
