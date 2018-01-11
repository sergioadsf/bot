package br.com.sergio.bot.util;

import java.util.HashMap;
import java.util.Map;

public class ConverterEmojiWeather {

	public final static Map<String, EmojiUtil> emojisWeather = new HashMap<>();
	
	static {
		add("01d", EmojiUtil.BLACK_SUN_WITH_RAYS);
		add("01n", EmojiUtil.MOON);
		add("02d", EmojiUtil.SUN_BEHIND_CLOUD);
		add("02n", EmojiUtil.CLOUD);
		add("03d", EmojiUtil.CLOUD);
		add("04d", EmojiUtil.CLOUD);
		add("04n", EmojiUtil.CLOUD);
		add("09d", EmojiUtil.CLOUD_RAIN);
		add("09n", EmojiUtil.CLOUD_RAIN);
		add("10d", EmojiUtil.CLOUD_RAIN);
		add("10n", EmojiUtil.CLOUD_RAIN);
		add("11d", EmojiUtil.THUNDERSTORM);
		add("11n", EmojiUtil.THUNDERSTORM);
		add("13d", EmojiUtil.SNOWFLAKE);
		add("13n", EmojiUtil.SNOWFLAKE);
	}
	
	private static void add(String key, EmojiUtil emoji) {
		emojisWeather.put(key, emoji);
	}
	
	public static EmojiUtil get(String key) {
		return emojisWeather.get(key);
	}
}
