package br.com.sergio.bot.service.football.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;

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
		String url = String.format("%s/%s/%d", getBotConfig().getWsUrlFootball(), CLASSIFICATION, tipoCampeonato); 
		String json = this.getConnectService().get(url);
		Response<List<TeamPosition>> response = strToObj(json, new TypeReference<Response<List<TeamPosition>>>() {
		});

		if (!response.isSuccess()) {
			throw new NotFoundException();
		}

		return response.getResponse();
	}

}
