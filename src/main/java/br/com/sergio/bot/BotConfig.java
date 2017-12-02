package br.com.sergio.bot;

public class BotConfig {
	
	static final String USER = "SergioTest16Bot";
	private static final String TOKEN = "TOKEN_BOT_SERGIO_TESTE_16";
	
	public static final String getToken() {
		return System.getenv(BotConfig.TOKEN);
	}
	

}
