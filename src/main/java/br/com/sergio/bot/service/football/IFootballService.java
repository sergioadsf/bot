package br.com.sergio.bot.service.football;

import java.util.List;

import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.model.football.Match;
import br.com.sergio.bot.model.football.TeamPosition;

public interface IFootballService {

	final String CALENDAR = "calendario";
	final String CLASSIFICATION = "classificacao";
	final String ROUND = "rodada";

	List<Match> findRound(FootSearch fooatSearch) throws Exception;

	List<TeamPosition> findClassification(int tipoCampeonato) throws Exception;

}
