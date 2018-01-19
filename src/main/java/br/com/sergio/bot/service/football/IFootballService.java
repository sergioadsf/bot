package br.com.sergio.bot.service.football;

import java.util.List;

import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.model.football.Match;
import br.com.sergio.bot.model.football.TeamPosition;

public interface IFootballService {

	final String CALENDAR = "calendario";
	final String CLASSIFICATION = "classificacao";
	final String ROUND = "rodada";
	final String GROUP = "grupo";
	final String TEAM = "time";

	List<Match> findRound(FootSearch fooatSearch) throws Exception;

	List<TeamPosition> findClassification(int tipoCampeonato) throws Exception;

	List<TeamPosition> findGroup(FootSearch fooatSearch) throws Exception;
	
	List<TeamPosition> findTeam(FootSearch fooatSearch) throws Exception;

	List<String> findGroups(FootSearch footSearch)  throws Exception;

}
