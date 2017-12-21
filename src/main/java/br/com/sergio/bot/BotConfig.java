package br.com.sergio.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BotConfig {

	@Value("${api.user}")
	private String apiUser;

	@Value("${api.key}")
	private String apiKey;

	public final String getToken() {
		return apiKey;
	}
	
	public final String getUser() {
		return apiUser;
	}

}
