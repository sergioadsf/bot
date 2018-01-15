package br.com.sergio.bot.model.weather;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sergio.bot.model.DTO;

@SuppressWarnings("serial")
@JsonIgnoreProperties({ "" })
public class City implements DTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("country")
	private String country;

	@JsonProperty("population")
	private BigDecimal population;

	@JsonProperty("coord")
	private Coordinates coordinate;

	public City() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

	public BigDecimal getPopulation() {
		return population;
	}

	public Coordinates getCoordinate() {
		return coordinate;
	}

}
