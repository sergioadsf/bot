package br.com.sergio.bot.service.football;

import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.service.IService;
import br.com.sergio.bot.util.MarkdownWriter;

public interface IBotFootballService extends IService {

	void findRound(AbsSender absSender, MarkdownWriter msg, FootSearch footSearch) throws Exception;

	void askRound(AbsSender absSender, MarkdownWriter msg);

}
