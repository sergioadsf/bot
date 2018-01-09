package br.com.sergio.bot.model.football;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sergio.bot.model.DTO;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match implements DTO {

	@JsonProperty("rodada")
	private Integer round;

	@JsonProperty("tipo_campeonato")
	private Integer competition;

	@JsonProperty("info")
	private String info;

	@JsonProperty("estadio")
	private String stadium;

	@JsonProperty("data")
	private String date;

	@JsonProperty("mandante")
	private Team principal;

	@JsonProperty("visitante")
	private Team visitor;

	public Match() {
	}

	public Match(Integer round, Integer competition, String info, String stadium, String date, Team principal,
			Team visitor) {
		super();
		this.round = round;
		this.competition = competition;
		this.info = info;
		this.stadium = stadium;
		this.date = date;
		this.principal = principal;
		this.visitor = visitor;
	}

	public Integer getRound() {
		return round;
	}

	public Integer getCompetition() {
		return competition;
	}

	public String getInfo() {
		return info;
	}

	public String getStadium() {
		return stadium;
	}

	public String getDate() {
		return date;
	}

	public Team getPrincipal() {
		return principal;
	}

	public Team getVisitor() {
		return visitor;
	}

}
