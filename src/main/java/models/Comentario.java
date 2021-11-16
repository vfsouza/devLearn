package models;

import java.io.Serializable;

public class Comentario implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String autor;
	private int avaliacao;
	private String conteudo;
	private int moduloId;
	
	public Comentario() {
		id = -1;
		autor = "";
		avaliacao = -1;
		conteudo = "";
		moduloId = -1;
	}

	public Comentario(int id, String autor, int avaliacao, String conteudo, int moduloId) {
		super();
		this.id = id;
		this.autor = autor;
		this.avaliacao = avaliacao;
		this.conteudo = conteudo;
		this.moduloId = moduloId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public int getModuloId() {
		return moduloId;
	}

	public void setModuloId(int moduloId) {
		this.moduloId = moduloId;
	}

	public String toString() {
		return "Comentario [id=" + id + ", autor=" + autor + ", avaliacao=" + avaliacao + ", conteudo=" + conteudo
				+ ", moduloId=" + moduloId + "]";
	}
}