package br.com.sergio.bot.util;

public class MarkdownWriter {

	private StringBuilder message;

	private MarkdownWriter() {
		this.message = new StringBuilder();
	}

	public static MarkdownWriter start() {
		return new MarkdownWriter();
	}

	public MarkdownWriter append(Object text) {
		this.message.append(text);
		return this;
	}

	public MarkdownWriter graus(Object text) {
		return append(text).append(" ºC");
	}

	public MarkdownWriter porcentagem(Object text) {
		return append(text).append(" %");
	}

	public MarkdownWriter emoji(EmojiUtil emoji) {
		return append(" ").append(emoji.toString()).append(" ");
	}

	public MarkdownWriter newLine() {
		return append("\n");
	}

	public MarkdownWriter bold(String text) {
		return append("*").append(text).append("*");
	}

	public MarkdownWriter italic(String text) {
		return append("_").append(text).append("_");
	}
	
	public MarkdownWriter capitalize(String description) {
		return append(description.substring(0, 1).toUpperCase()).append(description.substring(1));
	}

	public MarkdownWriter image(String url) {
		return append("[.](").append(url).append(")");
	}

	public MarkdownWriter image(String title, String url) {
		return append("[").append(title).append("](").append(url).append(")");
	}
	
	public String get() {
		return message.toString();
	}


}
