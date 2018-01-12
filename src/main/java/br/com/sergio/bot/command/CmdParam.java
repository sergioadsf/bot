package br.com.sergio.bot.command;

public enum CmdParam {

	START_CMD("/start"), 
	RESULT_CMD("/resultado"), 
	ANALYZE_CMD("/analyze"), 
	LOCATION_CMD("/location"), 
	WEATHER_CMD("/clima"), 
	CLASSIFICATION_CMD("/classificacao"), 
	CANCEL_CMD("Cancelar");

	private String value;

	private CmdParam(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static CmdParam param(String value) {
		for (CmdParam param : values()) {
			if (value.equals(param.value)) {
				return param;
			}
		}
		return null;
	}

}
