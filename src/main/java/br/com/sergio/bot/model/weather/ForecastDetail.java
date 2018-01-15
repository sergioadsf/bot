package br.com.sergio.bot.model.weather;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sergio.bot.model.DTO;

@SuppressWarnings("serial")
@JsonIgnoreProperties({ "clouds", "rain", "sys", "snow" })
public class ForecastDetail implements DTO {

	@JsonProperty("dt")
	private Long date;

	@JsonProperty("dt_txt")
	private String dateStr;

	@JsonProperty("weather")
	private List<Weather> weather;

	@JsonProperty("main")
	private Main main;

	@JsonProperty("wind")
	private Wind wind;

	public ForecastDetail() {
		weather = new ArrayList<>();
	}

	public Long getDate() {
		return date;
	}

	public String getDateStr() {
		return dateStr;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public Main getMain() {
		return main;
	}

	public Wind getWind() {
		return wind;
	}

}
