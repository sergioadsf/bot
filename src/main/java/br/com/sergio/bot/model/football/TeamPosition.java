package br.com.sergio.bot.model.football;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sergio.bot.model.DTO;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamPosition implements DTO {

	@JsonProperty("grupo")
	private String group;

	@JsonProperty("tipo_campeonato")
	private Integer competition;

	@JsonProperty("posicao")
	private Integer position;

	@JsonProperty("link")
	private String link;

	@JsonProperty("time_nome")
	private String name;

	@JsonProperty("alias")
	private String alias;

	@JsonProperty("variacao")
	private Double variation;

	@JsonProperty("pontos")
	private Integer points;

	@JsonProperty("jogos")
	private Integer matches;

	@JsonProperty("vitorias")
	private Integer wins;

	@JsonProperty("empates")
	private Integer draws;

	@JsonProperty("derrotas")
	private Integer loses;

	@JsonProperty("gols_pro")
	private Integer scoresPro;

	@JsonProperty("gols_contra")
	private Integer scoresAgainst;

	@JsonProperty("saldo_gols")
	private Integer balance;

	@JsonProperty("perc_ult_jogos")
	private Double lastPlays;

	public TeamPosition() {
	}

	public TeamPosition(String group, Integer competition, Integer position, String link, String name, String alias,
			Double variation, Integer points, Integer matches, Integer wins, Integer draws, Integer loses,
			Integer scoresPro, Integer scoresAgainst, Integer balance, Double lastPlays) {
		super();
		this.group = group;
		this.competition = competition;
		this.position = position;
		this.link = link;
		this.name = name;
		this.alias = alias;
		this.variation = variation;
		this.points = points;
		this.matches = matches;
		this.wins = wins;
		this.draws = draws;
		this.loses = loses;
		this.scoresPro = scoresPro;
		this.scoresAgainst = scoresAgainst;
		this.balance = balance;
		this.lastPlays = lastPlays;
	}

	public String getGroup() {
		return group;
	}

	public Integer getCompetition() {
		return competition;
	}

	public Integer getPosition() {
		return position;
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}

	public String getAlias() {
		return alias;
	}

	public Double getVariation() {
		return variation;
	}

	public Integer getPoints() {
		return points;
	}

	public Integer getMatches() {
		return matches;
	}

	public Integer getWins() {
		return wins;
	}

	public Integer getDraws() {
		return draws;
	}

	public Integer getLoses() {
		return loses;
	}

	public Integer getScoresPro() {
		return scoresPro;
	}

	public Integer getScoresAgainst() {
		return scoresAgainst;
	}

	public Integer getBalance() {
		return balance;
	}

	public Double getLastPlays() {
		return lastPlays;
	}

}
