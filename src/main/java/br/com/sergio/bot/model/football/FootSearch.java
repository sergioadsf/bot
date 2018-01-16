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

	@JsonProperty("grupo")
	private String group;

	@JsonProperty("time")
	private String team;

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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

}
