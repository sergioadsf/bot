package br.com.sergio.bot.service.impl;

import java.io.IOException;

import org.cogroo.text.Document;
import org.cogroo.text.impl.DocumentImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sergio.bot.BotCogroo;
import br.com.sergio.bot.BotConfig;
import br.com.sergio.bot.service.IConnectService;
import br.com.sergio.bot.weather.model.DTO;

public abstract class AbsService {

	@Autowired
	private BotCogroo botCogroo;

	@Autowired
	private BotConfig botConfig;

	@Autowired
	private IConnectService connectService;

	protected <E extends DTO> E strToObj(String str, Class<E> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(str, clazz);
	}
	
	protected <E extends DTO> E strToObj(String str, TypeReference<E> type)
			throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(str, type);
	}

	protected IConnectService getConnectService() {
		return connectService;
	}

	protected BotConfig getBotConfig() {
		return botConfig;
	}

	protected Document analyze(String text) {
		Document document = new DocumentImpl();
		document.setText(text);
		botCogroo.analyze(document);

		return document;
	}

}
