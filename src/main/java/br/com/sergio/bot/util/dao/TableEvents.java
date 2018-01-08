package br.com.sergio.bot.util.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.sergio.bot.util.EmojiUtil;
import br.com.sergio.bot.util.MarkdownWriter;

/**
 * Emulating a table on database
 * 
 * @author sergio
 *
 */
public class TableEvents {

	private final static Map<Integer, Map<Integer, String>> mapEvents = new HashMap<>();

	static {
		mapEvents.put(01, getDecember());
		mapEvents.put(12, getDecember());
	}

	private static final Map<Integer, String> getDecember() {
		Map<Integer, String> map = newMap();
		MarkdownWriter msg = MarkdownWriter.start();
		msg.append("Ei, estou passando só para desejar ")
			.bold("feliz natal")
			.append(" e boas festas!!")
			.append(EmojiUtil.CHRISTMAS_TREE)
			.append(EmojiUtil.CHRISTMAS_FACE)
			.append(EmojiUtil.BEER_MUGS)
			.append(EmojiUtil.WRAPPED_PRESENT)
			.image("https://media.tenor.com/images/86030cb6abe774ab11b43753576ad1ea/tenor.gif");
		map.put(24, msg.get());

		// map.put(31, "");
		return map;
	}

	private static final Map<Integer, String> getJanuary() {
		Map<Integer, String> map = newMap();
		map.put(24, "");
		return map;
	}

	private static Map<Integer, String> newMap() {
		Map<Integer, String> map = new HashMap<>();
		return map;
	}

	public static Map<Integer, Map<Integer, String>> getMapevents() {
		return mapEvents;
	}

}
