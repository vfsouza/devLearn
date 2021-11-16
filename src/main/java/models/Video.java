package models;

import java.io.Serializable;

public class Video implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String titulo;
	private String url;
	private int duracao;
	private String descricao;
	private int moduloId;
	
	public Video() {
		id = -1;
		titulo = "";
		url = "";
		duracao = 0;
		descricao = "";
		moduloId = -1;
	}

	public Video(int id, String titulo, String url, int duracao, String descricao, int moduloId) {
		this.id = id;
		this.titulo = titulo;
		this.url = url;
		this.duracao = duracao;
		this.descricao = descricao;
		this.moduloId = moduloId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getModuloId() {
		return moduloId;
	}

	public void setModuloId(int moduloId) {
		this.moduloId = moduloId;
	}

	public String toString() {
		return "Video [id=" + id + ", titulo=" + titulo + ", url=" + url + ", duracao=" + duracao + ", descricao="
				+ descricao + ", moduloId=" + moduloId + "]";
	}	
}