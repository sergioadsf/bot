package br.com.sergio.bot.service.football;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.model.football.TipoCampeonato;
import br.com.sergio.bot.service.IService;
import br.com.sergio.bot.util.MarkdownWriter;

public interface IBotFootballService extends IService {

	void findRound(AbsSender absSender, Message message) throws Exception;

	void askRound(AbsSender absSender, Message message, MarkdownWriter msg , TipoCampeonato tipo);

}
