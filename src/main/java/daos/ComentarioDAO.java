package daos;

import models.Comentario;

import java.sql.*;

public class ComentarioDAO {
	private Connection conexao;
	
	public ComentarioDAO() {
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
	
	public int getMaxIdComentario() {
		int maxId = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT id FROM comentario");
			rs.absolute(-1);
			maxId = rs.getInt("id");
			System.out.println(maxId);
			st.close();
			if (maxId >= 0) {
				maxId++;
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
	
	//inserir um comentario
	public void add(Comentario c) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO comentario (id, autor, avaliacao, conteudo, moduloId) "
				       + "VALUES(" + c.getId() + ", '" + c.getAutor() + "', '" + c.getAvaliacao() 
				       + "', '" + c.getConteudo() + "', '" +  c.getModuloId() + ");");
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
	
	//remover um comentario
	//OBS: falta validar se usuario pode 
	public void remove(Comentario c) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM comentario WHERE id = " + c.getId());
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
	
	//remover os comentarios relacionados a um modulo deletado
	public void removeAll(int moduloId) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM comentario WHERE moduloId = " + moduloId);
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
	
	//seleciona e retorna todos os comentario relacionados a um modulo
	public Comentario[] getAll(int moduloId) {
		Comentario comentario[] = new Comentario[1000];
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//OBS: conferir o comando SQL abaixo
			ResultSet rs = st.executeQuery("SELECT * FROM comentario WHERE moduloId = " + moduloId);		
	         if(rs.next()){
	             rs.last();
	             comentario = new Comentario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 comentario[i] = new Comentario(rs.getInt("id"), rs.getString("autor"), rs.getInt("avaliacao"), rs.getString("conteudo"), rs.getInt("moduloId"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return comentario;
	}
}
