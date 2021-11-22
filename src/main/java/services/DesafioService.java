package services;

import daos.DesafioDAO;
import models.Desafio;
import spark.Request;
import spark.Response;

public class DesafioService {
	private DesafioDAO DesafioDAO;

	public DesafioService() {
		DesafioDAO = new DesafioDAO();
		DesafioDAO.conectar();
	}
	
	public Object add(Request request, Response response) {
		String titulo = request.queryParams("titulo").toString();
		String questao = request.queryParams("questao").toString();
		int idModulo = Integer.parseInt(request.queryParams("idModulo"));

	    response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");

		int id = DesafioDAO.getMaxIdDesafio();
		
		Desafio desafio = new Desafio(id, titulo, questao, idModulo);

		DesafioDAO.add(desafio);

		response.status(201);
		return 0;
	}
}