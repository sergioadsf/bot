package br.com.sergio.bot.service.football.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.sergio.bot.exception.NotFoundException;
import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.model.football.Match;
import br.com.sergio.bot.model.football.TeamPosition;
import br.com.sergio.bot.model.weather.Response;
import br.com.sergio.bot.service.football.IFootballService;
import br.com.sergio.bot.service.impl.AbsService;

@Service
public class FootballService extends AbsService implements IFootballService {

	@Override
	public List<Match> findRound(FootSearch fooatSearch) throws Exception {
		String url = getBotConfig().getWsUrlFootball() + "/" + CALENDAR + "/" + ROUND;
		String json = this.getConnectService().post(url, objToStr(fooatSearch));
		Response<List<Match>> response = strToObj(json, new TypeReference<Response<List<Match>>>() {
		});

		if (!response.isSuccess()) {
			throw new NotFoundException();
		}

		return response.getResponse();
	}

	@Override
	public List<TeamPosition> findClassification(int tipoCampeonato) throws Exception {
		String url = String.format("%s/%s/", getBotConfig().getWsUrlFootball(), CLASSIFICATION);
		String json = this.getConnectService().get(url, String.valueOf(tipoCampeonato));
		Response<List<TeamPosition>> response = strToObj(json, new TypeReference<Response<List<TeamPosition>>>() {
		});

		if (!response.isSuccess()) {
			throw new NotFoundException();
		}

		return response.getResponse();
	}

	@Override
	public List<TeamPosition> findGroup(FootSearch fooatSearch) throws Exception {
		String url = getBotConfig().getWsUrlFootball() + "/" + CLASSIFICATION + "/" + GROUP;
		return post(fooatSearch, url);
	}

	@Override
	public List<TeamPosition> findTeam(FootSearch fooatSearch) throws Exception {
		String url = getBotConfig().getWsUrlFootball() + "/" + CLASSIFICATION + "/" + TEAM;
		return post(fooatSearch, url);
	}

	private List<TeamPosition> post(FootSearch fooatSearch, String url)
			throws Exception, JsonParseException, JsonMappingException, IOException, NotFoundException {
		String json = this.getConnectService().post(url, objToStr(fooatSearch));
		Response<List<TeamPosition>> response = strToObj(json, new TypeReference<Response<List<TeamPosition>>>() {
		});

		if (!response.isSuccess()) {
			throw new NotFoundException();
		}

		return response.getResponse();
	}

	@Override
	public List<String> findGroups(FootSearch footSearch) throws Exception {
		String url = String.format("%s/%s/%d", getBotConfig().getWsUrlFootball(), GROUP, footSearch.getCompetition());
		String json = this.getConnectService().get(url);
		Response<List<String>> response = strToObj(json, new TypeReference<Response<List<String>>>() {
		});

		if (!response.isSuccess()) {
			throw new NotFoundException();
		}

		return response.getResponse();
	}

}
