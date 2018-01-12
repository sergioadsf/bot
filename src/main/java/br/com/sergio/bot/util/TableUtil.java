package br.com.sergio.bot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TableUtil {

	private List<Field> list;
	private int sizeTotal;
	// private StringBuilder text;

	private TableUtil() {
		this.list = new ArrayList<>();
		// this.text = new StringBuilder();
	}

	public static TableUtil start() {
		return new TableUtil();
	}

	public TableUtil add(Object value, int size) {
		return add(value, size, false);
	}

	public TableUtil add(Object value, int size, boolean isLeft) {
		this.sizeTotal += size;
		this.list.add(new Field(value, size, isLeft));
		// int sbSize = this.text.length();
		return this;
	}

	public String build() {
		return this.buildText();
	}

	private String buildText() {
		StringBuilder sb = new StringBuilder();

		sb.append(IntStream.range(0, sizeTotal).mapToObj(i -> " ").collect(Collectors.joining("")));
		for (int index = list.size() - 1; index >= 0; index--) {
			Field field = list.get(index);
			String valueStr = field.value.toString();
			int start = 0;
			if (field.isLeft) {
				start = sizeTotal - field.size;
				sb.replace(start, start + valueStr.length(), valueStr);
			} else {
				start = sizeTotal - valueStr.length();
				sb.replace(start, start + valueStr.length(), valueStr);
			}
			sizeTotal -= field.size;
		}
		return sb.toString();
	}

	class Field {
		private Object value;
		private int size;
		private boolean isLeft;

		public Field(Object value, int size, boolean isLeft) {
			super();
			this.value = value;
			this.size = size;
			this.isLeft = isLeft;
		}

	}
}
