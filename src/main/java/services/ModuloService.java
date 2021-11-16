package services;

import daos.ModuloDAO;
import models.Curso;
import models.Modulo;
import spark.Request;
import spark.Response;

public class ModuloService {
	private ModuloDAO ModuloDAO;
	private Curso curso;

	public ModuloService() {
		ModuloDAO = new ModuloDAO();
		ModuloDAO.conectar();
	}
	
	public Object add(Request request, Response response) {
		int horas = Integer.parseInt(request.queryParams("horas"));
		String nome = request.queryParams("nome").toString();
		String topico = request.queryParams("topico").toString();
		
		int id = ModuloDAO.getMaxIdModulo();
		int cursoId = curso.getId();//talbez funcione
		
		Modulo modulo = new Modulo(id, horas, nome, topico, cursoId);

		ModuloDAO.add(modulo);

	    response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
		response.status(201); // 201 Created
		return "" + id;
	}
}