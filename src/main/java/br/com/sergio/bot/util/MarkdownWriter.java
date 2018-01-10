package br.com.sergio.bot.util;

public class MarkdownWriter {

	private Long chatId;
	private Integer userId;
	private String name;
	private StringBuilder message;

	private MarkdownWriter() {
		this(null);
	}

	private MarkdownWriter(Long chatId) {
		this.chatId = chatId;
		this.message = new StringBuilder();
	}

	public static MarkdownWriter start() {
		return new MarkdownWriter();
	}

	public static MarkdownWriter start(Long id) {
		return new MarkdownWriter(id);
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

	public MarkdownWriter bold(Object text) {
		return append("*").append(text).append("*");
	}

	public MarkdownWriter space() {

		return space(1);
	}

	public MarkdownWriter space(int qtd) {
		for (int cont = 0; cont < qtd; cont++)
			append(" ");

		return this;
	}

	public MarkdownWriter repeat(String value, int qtd) {
		for (int cont = 0; cont < qtd; cont++)
			append(value);

		return this;
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

	public MarkdownWriter name(String name) {
		this.name = name;
		return this;
	}

	public MarkdownWriter userId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public String get() {
		return message.toString();
	}

	public Long getChatId() {
		return chatId;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

}
