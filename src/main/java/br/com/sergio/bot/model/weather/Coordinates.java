package br.com.sergio.bot.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sergio.bot.model.DTO;

@SuppressWarnings("serial")
public class Coordinates implements DTO {

	@JsonProperty("lat")
	private Double latitude;

	@JsonProperty("lon")
	private Double longitude;

	public Coordinates() {
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

}
