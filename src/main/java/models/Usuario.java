package models;

import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String senha;
	private String nome;
	private String sobrenome;
	
	public Usuario() {
		this.id = -1;
		this.email = "";
		this.senha = "";
		this.nome = "";
		this.sobrenome = "";
	}
	
	public Usuario(int id, String email, String nome, String sobrenome) {
		setId(id);
		setEmail(email);
		setNome(nome);
		setSobrenome(sobrenome);
	}
	
	public Usuario(int id, String email, String senha, String nome, String sobrenome) {
		setId(id);
		setEmail(email);
		setSenha(senha);
		setNome(nome);
		setSobrenome(sobrenome);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	
}
