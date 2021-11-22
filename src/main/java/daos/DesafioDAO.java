package daos;

import models.Desafio;
import java.sql.*;

public class DesafioDAO {
	private Connection conexao;
	
	public DesafioDAO() {
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
	
	public int getMaxIdDesafio() {
		int maxId = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT id FROM desafio");
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
	
	public void add(Desafio d) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO desafio (id, titulo, questao, moduloid) "
				       + "VALUES(" + d.getId() + ", '" + d.getTitulo() + "', '" + d.getQuestao() 
				       + "', " +  d.getModuloId() + ");");
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
	
	//remover desafio
	//OBS: falta confirir se usuario pode 
	public void remove(Desafio d) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM dasafio WHERE id = " + d.getId());
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
	
	//remover os desafios relacionados a um modulo deletado
	public void removeAll(int moduloId) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM dasafio WHERE moduloId = " + moduloId);
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
}