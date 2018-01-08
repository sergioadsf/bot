package br.com.sergio.bot.util;

public abstract class Writer {

	private StringBuilder message;

	protected Writer() {
		this.message = new StringBuilder();
	}

	public Writer append(Object text) {
		this.message.append(text);
		return this;
	}

	public Writer graus(Object text) {
		return append(text).append(" ºC");
	}

	public Writer porcentagem(Object text) {
		return append(text).append(" %");
	}

	public Writer emoction(EmojiUtil emoji) {
		return append(" ").append(emoji.toString()).append(" ");
	}

	public Writer newLine() {
		return append("\n");
	}

	public abstract Writer bold(String text);

	public abstract Writer italic(String text);

	public String get() {
		return message.toString();
	}

}
