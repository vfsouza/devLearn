package services;

import daos.VideoDAO;
import models.Video;
import models.Modulo;
import spark.Request;
import spark.Response;

public class VideoService {
	private VideoDAO VideoDAO;
	private Modulo modulo;

	public VideoService() {
		VideoDAO = new VideoDAO();
		VideoDAO.conectar();
	}
	
	public Object add(Request request, Response response) {
		String titulo = request.queryParams("titulo").toString();
		String url = request.queryParams("url").toString();
		int horas = Integer.parseInt(request.queryParams("horas"));
		String descricao = request.queryParams("descricao").toString();
		
		response.header("Access-Control-Allow-Origin", "*");

		int id = VideoDAO.getMaxIdVideo();
		int moduloId = modulo.getId();//talvez funcione
		
		Video video = new Video(id, titulo, url, horas, descricao, moduloId);

		VideoDAO.add(video);

		response.status(201); // 201 Created
		return 0;
	}
}