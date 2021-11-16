package models;

public class Desafio {
	private int id;
	private String titulo;
	private String questao;
	private int moduloId;
	
	public Desafio(){
		id = -1;
		titulo = "";
		questao = "";
		moduloId = -1;
	}
	
	public Desafio(int id, String titulo, String questao, int moduloId) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.questao = questao;
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
	public String getQuestao() {
		return questao;
	}
	public void setQuestao(String questao) {
		this.questao = questao;
	}
	
	public int getModuloId() {
		return moduloId;
	}

	public void setModuloId(int moduloId) {
		this.moduloId = moduloId;
	}

	public String toString() {
		return "Desafio [id=" + id + ", titulo=" + titulo + ", questao=" + questao + ", moduloId=" + moduloId + "]";
	}
}