package br.com.sergio.bot.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sergio.bot.BotConfig;
import br.com.sergio.bot.model.DTO;
import br.com.sergio.bot.service.IConnectService;

public abstract class AbsService {

	@Autowired
	private BotConfig botConfig;

	@Autowired
	private IConnectService connectService;
	
	protected <E extends DTO> E strToObj(String str, Class<E> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(str, clazz);
	}

	protected IConnectService getConnectService() {
		return connectService;
	}

	protected BotConfig getBotConfig() {
		return botConfig;
	}

}
