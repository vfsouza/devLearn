package daos;

import models.Curso;

import java.sql.*;


public class CursoDAO {
	private Connection conexao;

	public int getMaxIdCurso() {
		int maxId = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM curso");
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

	public CursoDAO() {
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

	public void add(Curso c) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO curso (id, idioma, autor, conteudo, url, titulo, horas, avaliacao, areaid) "
				       + "VALUES(" + c.getId() + ", '" + c.getIdioma() + "', '" + c.getAutor() + "', '" + c.getConteudo()
				       + "', '" + c.getURL() + "', '" + c.getTitulo()  + "', 1, 1, " +  c.getAreaId() + ");");
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}

//	public Produto get(int id) {
//		Produto produto = new Produto();
//		try {
//			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//			ResultSet rs = st.executeQuery("SELECT id, descricao, preco, quantidade, data_fab, data_val FROM carros WHERE id = " + id);
//			rs.next();
//			produto = new Produto(rs.getInt("id"), rs.getString("descricao"), rs.getFloat("preco"), rs.getInt("quantidade"), rs.getString("data_fab"), rs.getString("data_val"));
//			st.close();
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		return produto;
//	}

//	public void update(Produto p) {
//		try {  
//			Statement st = conexao.createStatement();
//			String sql = "UPDATE carros SET descricao = '" + p.getDescricao() + "', preco = '"  
//				       + p.getPreco() + "', quantidade = '" + p.getQuant() + "', data_fab = '"
//						 + p.getDataFabricacao() + "', data_val = '" + p.getDataValidade() + "'"
//					    + " WHERE id = " + p.getId();
//			st.executeUpdate(sql);
//			st.close();
//		} catch (SQLException u) {  
//			throw new RuntimeException(u);
//		}
//	}

//	public void remove(Produto p) {
//		try {  
//			Statement st = conexao.createStatement();
//			st.executeUpdate("DELETE FROM carros WHERE id = " + p.getId());
//			st.close();
//		} catch (SQLException u) {  
//			throw new RuntimeException(u);
//		}
//	}

	public Curso[] getAll() {
		Curso curso[] = new Curso[1000];
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM curso");		
	         if(rs.next()){
	             rs.last();
	             curso = new Curso[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                curso[i] = new Curso(rs.getInt("id"), rs.getString("idioma"), rs.getString("autor"), rs.getString("conteudo"), rs.getString("url"), rs.getString("titulo"), rs.getInt("horas"), rs.getInt("avaliacao"), rs.getInt("areaid"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return curso;
	}
}