package br.com.sergio.bot.model.football;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sergio.bot.model.DTO;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team implements DTO {

	@JsonProperty("sigla")
	private String initials;

	@JsonProperty("nome")
	private String name;

	@JsonProperty("img")
	private String img;

	@JsonProperty("gols")
	private Integer score;

	public Team() {
	}

	public Team(String initials, String name, String img, Integer score) {
		super();
		this.initials = initials;
		this.name = name;
		this.img = img;
		this.score = score;
	}

	public String getInitials() {
		return initials;
	}

	public String getName() {
		return name;
	}

	public String getImg() {
		return img;
	}

	public Integer getScore() {
		return score == null ? 0 : score;
	}

}
