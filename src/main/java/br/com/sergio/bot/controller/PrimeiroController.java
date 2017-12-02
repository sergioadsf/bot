package br.com.sergio.bot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sergio.bot.BotConfig;
import br.com.sergio.bot.model.Teste;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "primeiro")
public class PrimeiroController {

	@GetMapping(path = "/teste")
	public @ResponseBody Teste teste() {

		return new Teste(1L, BotConfig.getToken());
	}
}
