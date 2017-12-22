package br.com.sergio.bot.util;

public class MessageWriter {

	private StringBuilder message;

	private MessageWriter() {
		this.message = new StringBuilder();
	}

	public static MessageWriter start() {
		return new MessageWriter();
	}

	public MessageWriter append(Object text) {
		this.message.append(text);
		return this;
	}

	public MessageWriter emoction(EmojiUtil emoji) {
		return append(" ").append(emoji.toString()).append(" ");
	}

	public MessageWriter newLine() {
		return append("\n");
	}

	public MessageWriter bold(String text) {
		return append("*").append(text).append("*");
	}

	public MessageWriter italic(String text) {
		return append("_").append(text).append("_");
	}

	public String get() {
		return message.toString();
	}

}
