package br.com.sergio.bot.model.football;

import java.util.ArrayList;
import java.util.List;

public enum TipoRodada {

	ULTIMA(-99, "Última"), Primeira(-98, "Primeira"), Proxima(-97, "Próxima");

	private int value;
	private String name;

	private TipoRodada(int type, String name) {
		this.value = type;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static String[] names() {
		List<String> names = new ArrayList<>();
		for (TipoRodada type : values()) {
			names.add(type.name);
		}

		return names.toArray(new String[names.size()]);
	}

	public static TipoRodada contains(String text) {
		for (TipoRodada type : values()) {
			if (type.name.toLowerCase().equals(text.toLowerCase())) {
				return type;
			}
		}

		return null;
	}

	public static int value(String text) {
		text = text.toLowerCase();
		for (TipoRodada type : values()) {
			if (type.name.toLowerCase().equals(text)) {
				return type.value;
			}
		}

		return 0;
	}

}
