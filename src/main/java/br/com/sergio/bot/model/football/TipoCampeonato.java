package br.com.sergio.bot.model.football;

public enum TipoCampeonato {

	PAULISTA(1, "Paulista"), CARIOCA(2, ""), GOIANO(3, ""), LIBERTADORES(4, "");

	private int tipo;
	private String nome;

	private TipoCampeonato(int tipo, String nome) {
		this.tipo = tipo;
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public String getNome() {
		return nome;
	}

}
