package br.com.sergio.bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class Weather implements DTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("main")
	private String main;

	@JsonProperty("description")
	private String description;

	@JsonProperty("icon")
	private String icon;

	public Long getId() {
		return id;
	}

	public String getMain() {
		return main;
	}

	public String getDescription() {
		return description;
	}

	public String getIcon() {
		return icon;
	}

}
