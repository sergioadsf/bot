package br.com.sergio.bot.model.football;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sergio.bot.model.DTO;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FootSearch implements DTO {

	@JsonProperty("rodada")
	private Integer round;

	@JsonProperty("campeonato")
	private Integer competition;

	public FootSearch() {
	}

	public FootSearch(Integer competition) {
		super();
		this.competition = competition;
	}

	public FootSearch(Integer round, Integer competition) {
		super();
		this.round = round;
		this.competition = competition;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public Integer getCompetition() {
		return competition;
	}

	public void setCompetition(Integer competition) {
		this.competition = competition;
	}

}
