package services;

import daos.UsuarioDAO;
import models.Usuario;
import models.Perfil;
import spark.Request;
import spark.Response;

public class UsuarioService {
	private UsuarioDAO UsuarioDAO;

	public UsuarioService() {
		UsuarioDAO = new UsuarioDAO();
		UsuarioDAO.conectar();
	}
	
	public Object add(Request request, Response response) {
		String email = request.queryParams("email").toString();
		String senha = request.queryParams("senha").toString();
		String nome = request.queryParams("nome").toString();
		String sobrenome = request.queryParams("sobrenome").toString();
		String nickname = request.queryParams("nickname").toString();
		
		response.header("Access-Control-Allow-Origin", "*");

		int idUser = UsuarioDAO.getMaxIdUsuario();
		int idPerfil = UsuarioDAO.getMaxIdPerfil();
		
		Usuario usuario = new Usuario(idUser, email, senha, nome, sobrenome);
		Perfil perfil = new Perfil(idPerfil, idUser, 0, nickname);
		

		UsuarioDAO.add(usuario, perfil);

		response.status(201); // 201 Created
		return 0;
	}

	public String login(Request request, Response response) {
		String body = request.body().toString();
		body = body.replaceAll("email=", "");
		body = body.replaceAll("senha=", "");
		body = body.replaceAll("%40", "@");
		String login[] = body.split("&");
		
		Usuario user = UsuarioDAO.login(login[0], login[1]);
		if(user != null) {
			response.header("Access-Control-Allow-Origin", "*");
		    response.header("Content-Type", "application/");
		    response.header("Content-Encoding", "UTF-8");
		    String resultado = "{\"id\":" + user.getId() + ",\"email\":\"" + user.getEmail() + "\",\"nome\":\"" + user.getNome() + "\",\"sobrenome\":\"" + user.getSobrenome() + "\"}";
		    return resultado.toString();
		}
		else {
			response.header("Access-Control-Allow-Origin", "*");
		    response.header("Content-Type", "application/");
		    response.header("Content-Encoding", "UTF-8");
		    String res = "null";
		    return res;
		}
	}
}
