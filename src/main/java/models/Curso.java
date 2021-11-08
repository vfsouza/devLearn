package models;

import java.io.Serializable;

public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String idioma;
	private String autor;	
	private String conteudo;
	private String URL;
	private String titulo;
	private int horas;
	private int avaliacao;
	private int areaid;
	
	public Curso() {
		id = -1;
		idioma = "";
		autor = "";
		conteudo = "";
		URL = "";
		titulo = "";
	}
	
	public Curso(int id, String idioma, String autor, String conteudo, String URL, String titulo, int areaid) {
		setId(id);
		setIdioma(idioma);
		setAutor(autor);
		setConteudo(conteudo);
		setURL(URL);
		setTitulo(titulo);
		setAreaId(areaid);
	}

	public Curso(int id, String idioma, String autor, String conteudo, String URL, String titulo, int horas, int avaliacao, int areaid) {
		setId(id);
		setIdioma(idioma);
		setAutor(autor);
		setConteudo(conteudo);
		setURL(URL);
		setTitulo(titulo);
		setHoras(horas);
		setAvaliacao(avaliacao);
		setAreaId(areaid);
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public int getAreaId() {
		return areaid;
	}
	
	public void setAreaId(int area) {
		this.areaid = area;
	}


	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
}