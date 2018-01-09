package br.com.sergio.bot.service.impl;

import java.io.IOException;

//import org.cogroo.text.Document;
//import org.cogroo.text.impl.DocumentImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import br.com.sergio.bot.BotCogroo;
import br.com.sergio.bot.BotConfig;
import br.com.sergio.bot.model.DTO;
import br.com.sergio.bot.service.IConnectService;

public abstract class AbsService {

//	@Autowired
//	private BotCogroo botCogroo;

	@Autowired
	private BotConfig botConfig;

	@Autowired
	private IConnectService connectService;

	protected <E extends DTO> E strToObj(String str, Class<E> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		return getMapper().readValue(str, clazz);
	}

	private ObjectMapper getMapper() {
		return new ObjectMapper();
	}
	
	protected <E extends DTO> E strToObj(String str, TypeReference<E> type)
			throws JsonParseException, JsonMappingException, IOException {
		
		return getMapper().readValue(str, type);
	}
	
	protected <E extends DTO> String objToStr(E value)
			throws JsonParseException, JsonMappingException, IOException {
		
		return getMapper().writeValueAsString(value);
	}
	

	protected IConnectService getConnectService() {
		return connectService;
	}

	protected BotConfig getBotConfig() {
		return botConfig;
	}

//	protected Document analyze(String text) {
//		Document document = new DocumentImpl();
//		document.setText(text);
//		botCogroo.analyze(document);
//
//		return document;
//	}

}
