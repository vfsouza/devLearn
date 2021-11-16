package models;

import java.io.Serializable;

public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String foto;
	private String descricao;
	private int interesse;
	private String cursos_salvos;
	private int usuarioId;
	private int cursoId;
	private String nickname;
	
	public Perfil() {
		id = -1;
		foto = "";
		descricao = "";
		interesse = -1;
		cursos_salvos = "";
		usuarioId = -1;
		cursoId = -1;
		nickname = "";
	}
	
	public Perfil(int id, int usuarioId, int cursoId, String nickname) {
		setId(id);
		setUsuarioId(usuarioId);
		setCursoId(cursoId);
		setNickname(nickname);
	}
	
	public Perfil(int id, String descricao, int interesse, String cursos_salvos, int usuarioId, int cursoId, String nickname) {
		setId(id);
		setDescricao(descricao);
		setInteresse(interesse);
		setCursos_salvos(cursos_salvos);
		setUsuarioId(usuarioId);
		setCursoId(cursoId);
		setNickname(nickname);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getInteresse() {
		return interesse;
	}
	public void setInteresse(int interesse) {
		this.interesse = interesse;
	}
	public String getCursos_salvos() {
		return cursos_salvos;
	}
	public void setCursos_salvos(String cursos_salvos) {
		this.cursos_salvos = cursos_salvos;
	}
	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	public int getCursoId() {
		return cursoId;
	}
	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
