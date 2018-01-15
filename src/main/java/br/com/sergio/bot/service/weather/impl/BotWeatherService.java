package br.com.sergio.bot.service.weather.impl;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.exception.NotFoundException;
import br.com.sergio.bot.model.weather.City;
import br.com.sergio.bot.model.weather.CurrentForecast;
import br.com.sergio.bot.model.weather.Forecast;
import br.com.sergio.bot.model.weather.ForecastDetail;
import br.com.sergio.bot.model.weather.Main;
import br.com.sergio.bot.model.weather.Weather;
import br.com.sergio.bot.service.impl.AbsService;
import br.com.sergio.bot.service.weather.IBotWeatherService;
import br.com.sergio.bot.service.weather.IWeatherService;
import br.com.sergio.bot.util.ConverterEmojiWeather;
import br.com.sergio.bot.util.DateUtil;
import br.com.sergio.bot.util.EmojiUtil;
import br.com.sergio.bot.util.KeyboardUtil;
import br.com.sergio.bot.util.MarkdownWriter;

@Service
public class BotWeatherService extends AbsService implements IBotWeatherService {

	@Autowired
	private IWeatherService wService;

	@Override
	public void findCurrent(AbsSender absSender, MarkdownWriter msg, String text) {
		try {

			CurrentForecast weather = wService.findCurrent(text);

			sendCurrentMessage(absSender, msg, weather);
		} catch (NotFoundException e) {
			sendErrorMessage(absSender, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void findCurrent(AbsSender absSender, MarkdownWriter msg, Location location) throws Exception {
		CurrentForecast weather = wService.findCurrent(location.getLatitude(), location.getLongitude());
		sendCurrentMessage(absSender, msg, weather);
	}

	@Override
	public void forecast5Days(AbsSender absSender, MarkdownWriter msg, String text) throws Exception {
		try {

			Forecast forecast = wService.forecast5Days(text);

			sendForecast5Message(absSender, msg, forecast);
		} catch (NotFoundException e) {
			sendErrorMessage(absSender, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void askCity(AbsSender absSender, MarkdownWriter msg) {
		msg.newLine().append("Me diga o nome da cidade?").newLine();

		InlineKeyboardMarkup inlineKeyboard = KeyboardUtil.getListInlineKeyboard(msg.getUserId(), "");

		SendMessage answer = new SendMessage();
		answer.setReplyMarkup(inlineKeyboard);
		answer.setChatId(msg.getChatId());
		answer.setText(msg.get());
		answer.enableMarkdown(true);
		try {
			absSender.sendMessage(answer);

		} catch (TelegramApiException ex) {
			ex.printStackTrace();
		}

	}

	private void sendForecast5Message(AbsSender absSender, MarkdownWriter msg, Forecast forecast)
			throws TelegramApiException {
		City city = forecast.getCity();

		msg.newLine().append("A previsão para ").bold(String.format("%s (%s)", city.getName(), city.getCountry()))
				.append(" nos próximos dias:").newLine();

		Date data = DateUtil.minDateWithoutTime();
		for (ForecastDetail detail : forecast.getListForecast()) {
			Date anotherDate = DateUtil.dateWithoutTime(detail.getDate());
			if (data.compareTo(anotherDate) == 0) {
				continue;
			}
			
			data = anotherDate;
			Main main = detail.getMain();
			Weather w = detail.getWeather().get(0);
			msg.capitalize(w.getDescription()).newLine();
			msg.emoji(ConverterEmojiWeather.get(w.getIcon())).append("Atual: ").degrees(main.getTemperature())
					.newLine();
			msg.emoji(EmojiUtil.SNOWFLAKE).append("Minima: ").degrees(main.getMinTemperature()).newLine();
			msg.emoji(EmojiUtil.FIRE).append("Maxima: ").degrees(main.getMaxTemperature()).newLine();
			msg.emoji(EmojiUtil.SPLASHING_SWEAT_SYMBOL).append("Umidade: ").porcentagem(main.getHumidity()).newLine()
					.newLine();
		}

		// msg.image("http://openweathermap.org/img/w/" + w.getIcon() + ".png");

		msg.newLine().append("Gostaria de saber de mais alguma cidade? Se sim me diga o nome que irei lhe informar.");
		InlineKeyboardMarkup inlineKeyboard = KeyboardUtil.getListInlineKeyboard(msg.getUserId(), "");

		SendMessage answer = new SendMessage();
		answer.setChatId(msg.getChatId());
		answer.setText(msg.get());
		answer.enableMarkdown(true);
		answer.setReplyMarkup(inlineKeyboard);
		absSender.sendMessage(answer);
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

	private void sendCurrentMessage(AbsSender absSender, MarkdownWriter msg, CurrentForecast weather)
			throws TelegramApiException {

		Weather w = weather.getWeather().get(0);
		Main main = weather.getMain();

		msg.newLine().append("Clima em ").bold(weather.getName()).append(":").newLine();
		msg.capitalize(w.getDescription()).newLine();
		msg.emoji(ConverterEmojiWeather.get(w.getIcon())).append("Atual: ").degrees(main.getTemperature()).newLine();
		msg.emoji(EmojiUtil.SNOWFLAKE).append("Minima: ").degrees(main.getMinTemperature()).newLine();
		msg.emoji(EmojiUtil.FIRE).append("Maxima: ").degrees(main.getMaxTemperature()).newLine();
		msg.emoji(EmojiUtil.SPLASHING_SWEAT_SYMBOL).append("Umidade: ").porcentagem(main.getHumidity()).newLine();
		// msg.image("http://openweathermap.org/img/w/" + w.getIcon() + ".png");

		msg.newLine().append("Gostaria de saber de mais alguma cidade? Se sim me diga o nome que irei lhe informar.");
		InlineKeyboardMarkup inlineKeyboard = KeyboardUtil.getListInlineKeyboard(msg.getUserId(), "");

		SendMessage answer = new SendMessage();
		answer.setChatId(msg.getChatId());
		answer.setText(msg.get());
		answer.enableMarkdown(true);
		answer.setReplyMarkup(inlineKeyboard);
		absSender.sendMessage(answer);
	}

	private String getCity(String text) {
		// Document document = analyze(text);
		//
		// for (Sentence sentence : document.getSentences()) {
		// for (Token token : sentence.getTokens()) {
		// if (token.getChunkTag().contains("NP") &&
		// !token.getPOSTag().equals("pron-pers")
		// && !token.getPOSTag().equals("art") && !isKeywords(token.getLexeme())) {
		// return token.getLexeme();
		// }
		// }
		// }
		// String[] texts = text.split(" ");

		// return texts[1];
		return text;
	}

	// private boolean isKeywords(String token) {
	// for (String key : keywords) {
	// if (token.toLowerCase().equals(key)) {
	// return true;
	// }
	// }
	// return false;
	// }

	@Override
	public boolean isKeyword(String text) {
		for (String value : Arrays.asList("clima", "temp", "temperatura"))
			if (text.contains(value)) {
				return true;
			}
		return false;
	}

}
