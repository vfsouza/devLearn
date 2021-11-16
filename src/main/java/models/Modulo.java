package models;

import java.io.Serializable;

public class Modulo implements Serializable {
	private static final long serialVersionUID = 1L;
	private	int id;
	private int horas;
	private String nome;
	private String topico;
	private int cursoId;
	
	public Modulo() {
		id = -1;
		horas = 0;
		nome = "";
		topico = "";
		cursoId = -1;
	}
	
	public Modulo(int id, int horas, String nome, String topico, int cursoId) {
		this.id = id;
		this.horas = horas;
		this.nome = nome;
		this.topico = topico;
		this.cursoId = cursoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTopico() {
		return topico;
	}

	public void setTopico(String topico) {
		this.topico = topico;
	}

	public int getCursoId() {
		return cursoId;
	}

	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
	}

	public String toString() {
		return "Modulo [id=" + id + ", horas=" + horas + ", nome=" + nome + ", topico=" + topico + ", Curso_ID="
				+ cursoId + "]";
	}	
}