package daos;

import models.Usuario;
import models.Perfil;

import java.sql.*;

public class UsuarioDAO {
	private Connection conexao;
	
	public int getMaxIdUsuario() {
		int maxId = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT id FROM usuario");
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
	
	public UsuarioDAO() {
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

	public void add(Usuario u, Perfil p) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO usuario (id, email, senha, nome, sobrenome) "
				       + "VALUES(" + u.getId() + ", '" + u.getEmail() + "', '" + u.getSenha() 
				       + "', '" + u.getNome() + "', '" + u.getNome() + "');\n" + "INSERT INTO perfil " 
				       + "(id, usuarioid, cursoid, nickname) " + "VALUES(" + p.getId() + ", " 
				       + p.getUsuarioId() + ", " + p.getCursoId() + ", '" + p.getNickname() + "');");
			st.close();
		} catch (SQLException e) {  
			throw new RuntimeException(e);
		}
	}

	public Usuario login(String email, String senha) {
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE email = '" + email + "' AND senha = '" + senha + "';");
			rs.beforeFirst();
			if (rs.next()) {
				Usuario user = new Usuario(rs.getInt("id"), rs.getString("email"), rs.getString("nome"), rs.getString("sobrenome"));
				return user;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
