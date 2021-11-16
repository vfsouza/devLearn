package daos;

import models.Perfil;
import java.sql.*;

public class PerfilDAO {
	private Connection conexao;
	
	public int getMaxIdPerfil() {
		int maxId = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT id FROM perfil");
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
	
	public PerfilDAO() {
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
	
	
	
	//MEXER AINDA 
	
	
	
}