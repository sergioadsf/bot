package br.com.sergio.bot.model.football;

import java.util.ArrayList;
import java.util.List;

public enum TipoCampeonato {

	PAULISTA(1, "Paulista"), CARIOCA(2, "Carioca"), GOIANO(3, "Goiano"), LIBERTADORES(4, "Libertadores");

	private int value;
	private String name;

	private TipoCampeonato(int type, String name) {
		this.value = type;
		this.name = name;
	}

	public int getTipo() {
		return value;
	}

	public String getNome() {
		return name;
	}

	public static String[] names() {
		List<String> names = new ArrayList<>();
		for (TipoCampeonato type : values()) {
			names.add(type.name);
		}

		return names.toArray(new String[names.size()]);
	}

	public static TipoCampeonato contains(String text) {
		for (TipoCampeonato type : values()) {
			if (type.name.toLowerCase().equals(text.toLowerCase())) {
				return type;
			}
		}

		return null;
	}

	public static int value(String text) {
		text = text.toLowerCase();
		for (TipoCampeonato type : values()) {
			if (type.name.toLowerCase().equals(text)) {
				return type.value;
			}
		}
		
		return 0;
	}

}
