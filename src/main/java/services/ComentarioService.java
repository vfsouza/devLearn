package services;

import daos.ComentarioDAO;
import models.Comentario;
import models.Modulo;
import spark.Request;
import spark.Response;

public class ComentarioService {
	private ComentarioDAO ComentarioDAO;
	private Modulo modulo;

	public ComentarioService() {
		ComentarioDAO = new ComentarioDAO();
		ComentarioDAO.conectar();
	}
	
	public Object add(Request request, Response response) {
		String autor = request.queryParams("autor").toString();
		int avaliacao = Integer.parseInt(request.queryParams("avaliacao"));
		String conteudo = request.queryParams("conteudo").toString();
		
		response.header("Access-Control-Allow-Origin", "*");

		int id = ComentarioDAO.getMaxIdComentario();
		int moduloId = modulo.getId();//nao funciona
		
		Comentario comentario = new Comentario(id, autor, avaliacao, conteudo, moduloId);

		ComentarioDAO.add(comentario);

		response.status(201); // 201 Created
		return 0;
	}
}