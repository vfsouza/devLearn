package services;

import daos.VideoDAO;
import models.Video;
import spark.Request;
import spark.Response;

public class VideoService {
	private VideoDAO VideoDAO;

	public VideoService() {
		VideoDAO = new VideoDAO();
		VideoDAO.conectar();
	}
	
	public Object add(Request request, Response response) {
		String titulo = request.queryParams("titulo").toString();
		String url = request.queryParams("url").toString();
		int horas = Integer.parseInt(request.queryParams("horas"));
		String descricao = request.queryParams("descricao").toString();
		int idModulo = Integer.parseInt(request.queryParams("idModulo"));

	    response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");

		int id = VideoDAO.getMaxIdVideo();
		
		Video video = new Video(id, titulo, url, horas, descricao, idModulo);

		VideoDAO.add(video);

		response.status(201);
		return 0;
	}
}