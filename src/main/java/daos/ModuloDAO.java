package daos;

import models.Modulo;
import java.sql.*;

public class ModuloDAO {
	private Connection conexao;
	
	public ModuloDAO() {
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
	
	public int getMaxIdModulo() {
		int maxId = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT id FROM modulo");
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
	
	public void add(Modulo m) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO modulo (id, horas, nome, topico, cursoid) "
				       + "VALUES(" + m.getId() + ", " + m.getHoras() + ", '" + m.getNome() 
				       + "', '" + m.getTopico() + "', " +  m.getCursoId() + ");");
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
	
	public void remove(Modulo m) {
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM modulo WHERE id = " + m.getId());
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}	
	
	//remover os modulos relacionados a um curso deletado
	public void removeAll(int cursoId) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM modulo WHERE cursoId = " + cursoId);
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
	
	//retorna o modulo de acordo com seu id
	public Modulo get(int id) {
		Modulo modulo = new Modulo();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM modulo WHERE id = " + id);
			rs.next();
			modulo = new Modulo(rs.getInt("id"), rs.getInt("horas"), rs.getString("nome"), rs.getString("topico"), rs.getInt("cursoId"));
			st.close();
		} catch (Exception e) {
		System.err.println(e.getMessage());
		}
		return modulo;
	}
	
	//seleciona e retorna todos os modulos relacionados a um curso
	public Modulo[] getAll(int cursoId) {
		Modulo modulo[] = new Modulo[1000];
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//OBS: conferir o comando SQL abaixo
			ResultSet rs = st.executeQuery("SELECT * FROM modulo WHERE cursoId = " + cursoId);		
	         if(rs.next()){
	             rs.last();
	             modulo = new Modulo[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 modulo[i] = new Modulo(rs.getInt("id"), rs.getInt("horas"), rs.getString("nome"), rs.getString("topico"), rs.getInt("cursoId"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return modulo;
	}
}