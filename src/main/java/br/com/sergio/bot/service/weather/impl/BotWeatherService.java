package br.com.sergio.bot.service.weather.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.exception.NotFoundException;
import br.com.sergio.bot.service.impl.AbsService;
import br.com.sergio.bot.service.weather.IBotWeatherService;
import br.com.sergio.bot.service.weather.IWeatherService;
import br.com.sergio.bot.util.EmojiUtil;
import br.com.sergio.bot.util.MarkdownWriter;
import br.com.sergio.bot.weather.model.CurrentForecast;
import br.com.sergio.bot.weather.model.Main;
import br.com.sergio.bot.weather.model.Weather;

@Service
public class BotWeatherService extends AbsService implements IBotWeatherService {

	@Autowired
	private IWeatherService wService;

	@Override
	public void findCurrent(AbsSender absSender, Message message) {
		String cidade = this.getCity(message.getText());
		try {

			CurrentForecast weather = wService.findCurrent(cidade);
			
			sendDefaultMessage(absSender, message, weather);
		} catch (NotFoundException e) {
			sendErrorMessage(absSender, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void findCurrent(AbsSender absSender, Message message, Location location) throws Exception {
		CurrentForecast weather = wService.findCurrent(location.getLatitude(), location.getLongitude());
		sendDefaultMessage(absSender, message, weather);
	}

	private void sendErrorMessage(AbsSender absSender, Message message) {
		final User user = message.getFrom();
		final Chat chat = message.getChat();
		String userName = user.getFirstName() + " " + user.getLastName();
		MarkdownWriter msg = MarkdownWriter.start().bold(userName).newLine();
		Long chatId = chat.getId();
		
		msg.emoji(EmojiUtil.DISAPPOINTED_FACE).newLine();
		msg.append("Infelizmente ainda não conheço essa cidade!").newLine();

		SendMessage answer = new SendMessage();
		answer.setChatId(chatId.toString());
		answer.setText(msg.get());
		answer.enableMarkdown(true);
		try {
			absSender.sendMessage(answer);
		} catch (TelegramApiException ex) {
			ex.printStackTrace();
		}
	}

	private void sendDefaultMessage(AbsSender absSender, Message message, CurrentForecast weather)
			throws TelegramApiException {
		final User user = message.getFrom();
		final Chat chat = message.getChat();
		String userName = user.getFirstName() + " " + user.getLastName();
		MarkdownWriter msg = MarkdownWriter.start().bold(userName).newLine();
		Long chatId = chat.getId();
		
		Weather w = weather.getWeather().get(0);
		Main main = weather.getMain();

		msg.append("o clima em ").bold(weather.getName()).append(" nesse momento esta: ").newLine();
		msg.capitalize(w.getDescription()).newLine();
		msg.emoji(EmojiUtil.SNOWFLAKE).append("Minima:").graus(main.getMinTemperature()).newLine();
		msg.emoji(EmojiUtil.FIRE).append("Maxima: ").graus(main.getMaxTemperature()).newLine();
		msg.emoji(EmojiUtil.SPLASHING_SWEAT_SYMBOL).append("Umidade: ").porcentagem(main.getHumidity()).newLine();
		msg.image("http://openweathermap.org/img/w/" + w.getIcon() + ".png");

		SendMessage answer = new SendMessage();
		answer.setChatId(chatId);
		answer.setText(msg.get());

		answer.enableMarkdown(true);
		absSender.sendMessage(answer);
	}

	private String getCity(String text) {
//		Document document = analyze(text);
//
//		for (Sentence sentence : document.getSentences()) {
//			for (Token token : sentence.getTokens()) {
//				if (token.getChunkTag().contains("NP") && !token.getPOSTag().equals("pron-pers")
//						&& !token.getPOSTag().equals("art") && !isKeywords(token.getLexeme())) {
//					return token.getLexeme();
//				}
//			}
//		}
		String[] texts = text.split(" "); 

		return texts[1];
	}

	private boolean isKeywords(String token) {
		for (String key : keywords) {
			if (token.toLowerCase().equals(key)) {
				return true;
			}
		}
		return false;
	}

}
