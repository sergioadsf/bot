package br.com.sergio.bot.service.football;

import org.telegram.telegrambots.bots.AbsSender;

import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.service.IService;
import br.com.sergio.bot.util.MarkdownWriter;

public interface IBotFootballService extends IService {

	void findRound(AbsSender absSender, MarkdownWriter msg, FootSearch footSearch) throws Exception;

	void askRound(AbsSender absSender, MarkdownWriter msg);

	void findClassification(AbsSender absSender, MarkdownWriter msg, int competition);
	
	void findGroup(AbsSender absSender, MarkdownWriter msg, FootSearch footSearch);
	
	void findTeam(AbsSender absSender, MarkdownWriter msg, FootSearch footSearch);

	void ask(AbsSender absSender, MarkdownWriter msg, int value);

	void askGroup(AbsSender absSender, MarkdownWriter msg, FootSearch fs) throws Exception ;

}
