package services;

import daos.ModuloDAO;
import models.Modulo;
import spark.Request;
import spark.Response;

public class ModuloService {
	private ModuloDAO ModuloDAO;

	public ModuloService() {
		ModuloDAO = new ModuloDAO();
		ModuloDAO.conectar();
	}
	
	public Object add(Request request, Response response) {
		int horas = Integer.parseInt(request.queryParams("horas"));
		String nome = request.queryParams("nome").toString();
		String topico = request.queryParams("topico").toString();
		int cursoId = Integer.parseInt(request.queryParams("idCurso"));
		
		int id = ModuloDAO.getMaxIdModulo();
		
		Modulo modulo = new Modulo(id, horas, nome, topico, cursoId);

		ModuloDAO.add(modulo);

	    response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
		response.status(201);
		return "{\"id\":" + id + "}";
	}
}