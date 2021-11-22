package daos;

import models.Video;
import java.sql.*;

public class VideoDAO {
	private Connection conexao;
	
	public VideoDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "devlearn";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "postgres";
		String password = "679165";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public int getMaxIdVideo() {
		int maxId = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM video");
			rs.last();
			maxId = rs.getInt("id");
			System.out.println(maxId);
			st.close();
			if (maxId++ >= 0) {
				return maxId;
			}
			else {
				return maxId;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return maxId;
	}
	
	public void add(Video v) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO video (id, titulo, url, duracao, descricao, moduloid) "
				       + "VALUES(" + v.getId() + ", '" + v.getTitulo() + "', '" + v.getUrl() 
				       + "', " + v.getDuracao() + ", '" + v.getDescricao() + "', " + v.getModuloId() + ");");
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
	
	//remover video
	//OBS: falta confirir se usuario pode 
	public void remove(Video v) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM video WHERE id = " + v.getId());
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
	
	//remover os vidoes relacionados a um modulo deletado
	public void removeAll(int moduloId) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM video WHERE moduloId = " + moduloId);
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
	
	public Video get(int moduloId) {
		Video video = new Video();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM video WHERE moduloId = '" + moduloId + "';");
			rs.next();
			video = new Video(rs.getInt("id"), rs.getString("titulo"), rs.getString("url"), rs.getInt("duracao"), rs.getString("descricao"), rs.getInt("moduloId"));
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return video;
	}
}