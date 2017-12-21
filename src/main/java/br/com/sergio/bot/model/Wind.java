package br.com.sergio.bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class Wind implements DTO {

	@JsonProperty("speed")
	private Double speed;

	@JsonProperty("deg")
	private Double degrees;

	public Wind() {
	}

	public Double getSpeed() {
		return speed;
	}

	public Double getDegrees() {
		return degrees;
	}

}
