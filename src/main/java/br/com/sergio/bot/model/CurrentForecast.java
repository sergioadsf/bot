package br.com.sergio.bot.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentForecast implements DTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("cod")
	private Integer code;

	@JsonProperty("name")
	private String name;

	@JsonProperty("base")
	private String base;

	@JsonProperty("visibility")
	private BigDecimal visibility;

	@JsonProperty("wind")
	private Wind wind;

	@JsonProperty("weather")
	private List<Weather> weather;

	@JsonProperty("dt")
	private Long date;

	public CurrentForecast() {
		weather = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getBase() {
		return base;
	}

	public BigDecimal getVisibility() {
		return visibility;
	}

	public Wind getWind() {
		return wind;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public Long getDate() {
		return date;
	}

}
