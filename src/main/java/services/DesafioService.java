package services;

import daos.DesafioDAO;
import models.Desafio;
import models.Modulo;
import spark.Request;
import spark.Response;

public class DesafioService {
	private DesafioDAO DesafioDAO;
	private Modulo modulo;

	public DesafioService() {
		DesafioDAO = new DesafioDAO();
		DesafioDAO.conectar();
	}
	
	public Object add(Request request, Response response) {
		String titulo = request.queryParams("titulo").toString();
		String questao = request.queryParams("questao").toString();
		
		response.header("Access-Control-Allow-Origin", "*");

		int id = DesafioDAO.getMaxIdDesafio();
		int moduloId = modulo.getId();//talvez funcione
		
		Desafio desafio = new Desafio(id, titulo, questao, moduloId);

		DesafioDAO.add(desafio);

		response.status(201); // 201 Created
		return 0;
	}
}