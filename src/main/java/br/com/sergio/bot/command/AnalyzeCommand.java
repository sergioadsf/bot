package br.com.sergio.bot.command;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendLocation;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.sergio.bot.model.CurrentForecast;
import br.com.sergio.bot.model.Main;
import br.com.sergio.bot.model.Weather;
import br.com.sergio.bot.service.IWeatherService;
import br.com.sergio.bot.util.EmojiUtil;
import br.com.sergio.bot.util.MessageWriter;

@Component
public class AnalyzeCommand implements BotCommand {

	public static final String LOGTAG = "STARTCOMMAND";

	@Autowired
	private IWeatherService wService;

	public void execute(AbsSender absSender, Message message) {
		final User user = message.getFrom();
		final Chat chat = message.getChat();
		String userName = user.getFirstName() + " " + user.getLastName();
		MessageWriter msg = MessageWriter.start().bold(userName).newLine();
		Long chatId = chat.getId();
		try {

//			CurrentForecast weather = wService.findCurrent(message.getText());
//
//			Weather w = weather.getWeather().get(0);
//			Main main = weather.getMain();
//
//			msg.append(" o clima em ").append(weather.getName()).append(" nesse momento esta: ").newLine();
//			msg.append(w.getDescription()).newLine();
//			msg.append("Minima: ").append(main.getMinTemperature()).newLine();
//			msg.append("Maxima: ").append(main.getMaxTemperature()).newLine();
//			msg.append("Umidade: ").append(main.getHumidity()).newLine();
//			msg.append("http://openweathermap.org/img/w/").append(w.getIcon()).append(".png");
//
//			SendMessage answer = new SendMessage();
//			answer.setChatId(chat.getId());
//			answer.setText(msg.get());
//			answer.enableMarkdown(true);
			
			SendSticker s = new SendSticker();
			s.setChatId(chatId);
			s.setSticker("Smiling Face With Heart-Shaped Eye");
			File f =  new File(AnalyzeCommand.class.getResource("/static/sticker/corleone/corleone-romantico.jfif").toString().substring(6));
			s.setNewSticker(f);
			
//			SendLocation location = new SendLocation();
//			location.setChatId(chatId);
//			//-16.651556, -49.243090
//			location.setLatitude(-16.651556f);
//			location.setLongitude(-49.243090f);

			absSender.sendSticker(s);
//			absSender.sendLocation(location);
//			absSender.sendMessage(answer);
		} catch (Exception e1) {

			msg.emoction(EmojiUtil.DISAPPOINTED_FACE).newLine();
			msg.append("Infelizmente ainda não conheço essa cidade!").newLine();

			SendMessage answer = new SendMessage();
			answer.setChatId(chatId.toString());
			answer.setText(msg.get());
			answer.enableMarkdown(true);
			try {
				absSender.sendMessage(answer);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}

	}

}
