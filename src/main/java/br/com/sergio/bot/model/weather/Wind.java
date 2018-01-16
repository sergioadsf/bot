package br.com.sergio.bot.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sergio.bot.model.DTO;

@SuppressWarnings("serial")
public class Wind implements DTO {

	@JsonProperty("speed")
	private Double speed;

	@JsonProperty("deg")
	private Double degrees;
	
	@JsonProperty("gust")
	private Double gust;

	public Wind() {
	}

	public Double getSpeed() {
		return speed;
	}

	public Double getDegrees() {
		return degrees;
	}

}
