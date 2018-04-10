package br.com.tradutores.bean;

public class Token {
	
	private Integer id;
	
	private String lexema;
	
	private String padrao;
	
	private Integer escopo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public Integer getEscopo() {
		return escopo;
	}

	public void setEscopo(Integer escopo) {
		this.escopo = escopo;
	}

	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", padrao=" + padrao + "]";
	}
	
	

}
