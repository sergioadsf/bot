package br.com.sergio.bot.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StringUtil {
	
	public static final String alinhaE(String valor, int tamanho, String caracter) {
		StringBuilder auxValor = new StringBuilder(valor);
		for (int i = valor.length(); i < tamanho; i++) {
			auxValor.append(caracter);
		}
		return auxValor.toString();
	}

	public static final String alinhaE(Object valor, int tamanho) {
		return alinhaE(valor.toString(), tamanho, " ");
	}

	public static final String alinhaE(String valor, int tamanho) {
		return alinhaE(valor, tamanho, " ");
	}

	public static final String alinhaD(Object valor, int tamanho) {
		return alinhaD(valor.toString(), tamanho, " ");
	}

	public static final String alinhaD(String valor, int tamanho) {
		return alinhaD(valor, tamanho, " ");
	}

	public static final String alinhaD(String valor, int tamanho, String caracter) {
		String auxValor = valor;
		for (int i = valor.length(); i < tamanho; i++) {
			auxValor = caracter + auxValor;
		}
		return auxValor;
	}

	public static String tam(String entrada, int len) {
		if (entrada == null) {
			entrada = "";
		}
		int len_o = entrada.length();
		if (len_o > len) {
			return entrada.substring(0, len);
		} else if (len_o == len) {
			return entrada;
		} else {
			return alinhaE(entrada, len);
		}
	}

	public static final String formatar(double numero, String mascara) {
		DecimalFormat formatador = null;
		try {
			formatador = (DecimalFormat) NumberFormat.getInstance(new Locale("pt", "BR"));
		} catch (ClassCastException e) {
			return "";
		}
		formatador.applyPattern(mascara);
		return formatador.format(numero);
	}

	public static final String formatar(String numero, String mascara) {
		if (numero.trim().equals("")) {
			numero = "0";
		}
		try {
			return formatar(Double.parseDouble(numero), mascara);
		} catch (Exception e) {
		}
		return "";
	}

	public static final String formatar(int numero, String mascara) {
		return formatar(String.valueOf(numero), mascara);
	}

	public static List<Map<String, String>> quebrarLinha(String str, int lin, int col, String linhaVazia) {
		List<Map<String, String>> objArray = new ArrayList<>();

		int ini = 0;
		int fim = 0;
		int l = 0;
		int pos = 0;
		while (pos >= 0) {
			pos = str.indexOf("\r\n", ini);
			if (pos < 0) {
				fim = str.length() - 1;
			} else {
				fim = pos - 1;
			}

			int io = ini;
			int fo = fim - 1;
			while (fo != fim) {
				fo = io + col - 1;
				if (fo > fim) {
					fo = fim;
				}
				if (fo >= io) {
					l++;

					if ((l > lin) && (lin > 0)) {
						fo = fim;
						pos = -1;
					} else {
						Map<String, String> linha = new HashMap<>();
						linha.put("linha", encodeXml(str.substring(io, fo + 1)));

						objArray.add(linha);
					}
				}
				io = fo + 1;
			}
			ini = pos + 2;
		}
		for (; l < lin; l++) {
			Map<String, String> linha = new HashMap<>();
			linha.put("linha", linhaVazia);
			objArray.add(linha);
		}

		return objArray;
	}

	public static synchronized String encodeXml(String s) {
		StringBuilder encodedStr = new StringBuilder();

		if (s != null) {
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '&') {
					encodedStr.append("&amp;");
				} else if (s.charAt(i) == '"') {
					encodedStr.append("&quot;");
				} else if (s.charAt(i) == '>') {
					encodedStr.append("&gt;");
				} else if (s.charAt(i) == '<') {
					encodedStr.append("&lt;");
				} else {
					encodedStr.append(s.charAt(i));
				}
			}
		}
		return encodedStr.toString();
	}

	public static final String formatarTamanho(String texto, int tamanho) {
		if (texto.length() >= tamanho) {
			return texto.substring(0, tamanho);
		}
		return alinhaE(texto, tamanho, " ");
	}
	
}
