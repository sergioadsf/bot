package br.com.sergio.bot.service.football.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.exception.NotFoundException;
import br.com.sergio.bot.model.football.FootSearch;
import br.com.sergio.bot.model.football.Match;
import br.com.sergio.bot.model.football.Team;
import br.com.sergio.bot.model.football.TipoRodada;
import br.com.sergio.bot.service.football.IBotFootballService;
import br.com.sergio.bot.service.football.IFootballService;
import br.com.sergio.bot.service.impl.AbsService;
import br.com.sergio.bot.util.EmojiUtil;
import br.com.sergio.bot.util.KeyboardUtil;
import br.com.sergio.bot.util.MarkdownWriter;

@Service
public class BotFootballService extends AbsService implements IBotFootballService {

	@Autowired
	private IFootballService wService;

	@Override
	public void findRound(AbsSender absSender, MarkdownWriter msg, FootSearch footSearch) throws Exception {
		try {
			// TODO: back to improve this validation
			List<Match> listMatch = wService.findRound(footSearch);
			sendDefaultMessage(absSender, msg, listMatch);

		} catch (NotFoundException e) {
			sendErrorMessage(absSender, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void askRound(AbsSender absSender, MarkdownWriter msg) {

		msg.append("Informe a rodada desejada do campeonato! Selecione abaixo ou digite o numero da mesma.").newLine();

		ReplyKeyboard replyMarkup = new InlineKeyboardMarkup();
		replyMarkup = KeyboardUtil.getListInlineKeyboard(msg.getUserId(), "pt", TipoRodada.names());

		SendMessage answer = new SendMessage();
		answer.setReplyMarkup(replyMarkup);
		answer.setChatId(msg.getChatId());
		answer.setText(msg.get());
		answer.enableMarkdown(true);
		try {
			absSender.sendMessage(answer);

		} catch (TelegramApiException ex) {
			ex.printStackTrace();
		}
	}

	private void sendErrorMessage(AbsSender absSender, MarkdownWriter msg) {

		msg.emoji(EmojiUtil.DISAPPOINTED_FACE).newLine();
		msg.append("Infelizmente ainda não conheço essa cidade!").newLine();

		SendMessage answer = new SendMessage();
		answer.setChatId(msg.getChatId());
		answer.setText(msg.get());
		answer.enableMarkdown(true);
		try {
			absSender.sendMessage(answer);
		} catch (TelegramApiException ex) {
			ex.printStackTrace();
		}
	}

	private void sendDefaultMessage(AbsSender absSender, MarkdownWriter msg, List<Match> listMatch)
			throws TelegramApiException {

		String dateStr = "";
		for (Match match : listMatch) {
			Team principal = match.getPrincipal();
			Team visitor = match.getVisitor();
			String dateMatch = LocalDate.parse(match.getDate()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			if (!dateStr.equals(dateMatch)) {
				if (!"".equals(dateStr)) {
					msg.newLine();
				} else {
					msg.bold("Rodada nº: ").append(match.getRound()).newLine();
				}

				dateStr = dateMatch;
				msg.bold("Jogo(s) dia ").bold(dateMatch).newLine();
			}
			msg.append(principal.getName()).space().bold(principal.getScore()).append(" X ");
			msg.bold(visitor.getScore()).space().append(visitor.getName());
			msg.space().repeat("-", 3).space(2).append(match.getStadium()).newLine();
		}

		msg.newLine();
		askRound(absSender, msg);
	}

	@Override
	public boolean isKeyword(String text) {
		return Arrays.asList("campeonato", "resultado").contains(text.split(" ")[0]);
	}

}
