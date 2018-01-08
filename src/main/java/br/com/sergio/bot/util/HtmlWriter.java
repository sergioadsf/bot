package br.com.sergio.bot.util;

public class HtmlWriter {

	private StringBuilder message;

	private HtmlWriter() {
		this.message = new StringBuilder();
	}

	public static HtmlWriter start() {
		return new HtmlWriter();
	}

	public HtmlWriter append(Object text) {
		this.message.append(text);
		return this;
	}
	
	public HtmlWriter porcentagem(Object text) {
		return append(text).append(" %");
	}

	public HtmlWriter graus(Object text) {
		return append(text).append(" ºC");
	}

	public HtmlWriter emoji(EmojiUtil emoji) {
		return append(" ").append(emoji.toString()).append(" ");
	}

	public HtmlWriter newLine() {
		return append("\n");
	}

	public HtmlWriter bold(String text) {
		return append("<b>").append(text).append("</b>");
	}

	public HtmlWriter italic(String text) {
		return append("<i>").append(text).append("</i>");
	}

	public String get() {
		return message.toString();
	}

}
