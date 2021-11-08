package services;

import daos.CursoDAO;
import models.Curso;
import spark.Request;
import spark.Response;


public class CursoService {
	
	

	private CursoDAO CursoDAO;

	public CursoService() {
		CursoDAO = new CursoDAO();
		CursoDAO.conectar();
	}

	public Object add(Request request, Response response) {
		String idioma = request.queryParams("idioma").toString();
		String autor = request.queryParams("autor").toString();
		String conteudo = request.queryParams("conteudo").toString();
		String url = request.queryParams("url").toString();
		String titulo = request.queryParams("titulo").toString();
		int area = Integer.parseInt(request.queryParams("area"));

		int id = CursoDAO.getMaxIdCurso();
		
		Curso curso = new Curso(id, idioma, autor, conteudo, url, titulo, area);

		CursoDAO.add(curso);

	    response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
		response.status(201); // 201 Created
		return "" + id;
	}

//	public Object get(Request request, Response response) {
//		int id = Integer.parseInt(request.params(":id"));
//		
//		Curso produto = (Curso) CursoDAO.get(id);
//		
//		if (produto != null) {
//    	    response.header("Content-Type", "application/xml");
//    	    response.header("Content-Encoding", "UTF-8");
//
//            return "<produto>\n" + 
//            		"\t<id>" + produto.getId() + "</id>\n" +
//            		"\t<descricao>" + produto.getDescricao() + "</descricao>\n" +
//            		"\t<preco>" + produto.getPreco() + "</preco>\n" +
//            		"\t<quantidade>" + produto.getQuant() + "</quantidade>\n" +
//            		"\t<fabricacao>" + produto.getDataFabricacao() + "</fabricacao>\n" +
//            		"\t<validade>" + produto.getDataValidade() + "</validade>\n" +
//            		"</produto>\n";
//        } else {
//            response.status(404); // 404 Not found
//            return "Produto " + id + " não encontrado.";
//        }
//
//	}

//	public Object update(Request request, Response response) {
//        int id = Integer.parseInt(request.params(":id"));
//        
//		Curso produto = (Curso) CursoDAO.get(id);
//
//        if (produto != null) {
//        	produto.setDescricao(request.queryParams("descricao"));
//        	produto.setPreco(Float.parseFloat(request.queryParams("preco")));
//        	produto.setQuant(Integer.parseInt(request.queryParams("quantidade")));
//        	produto.setDataFabricacao(request.queryParams("dataFabricacao").toString());
//        	produto.setDataValidade(request.queryParams("dataValidade").toString());
//
//        	CursoDAO.update(produto);
//        	
//            return id;
//        } else {
//            response.status(404); // 404 Not found
//            return "Produto não encontrado.";
//        }
//
//	}

//	public Object remove(Request request, Response response) {
//        int id = Integer.parseInt(request.params(":id"));
//
//        Curso produto = (Curso) CursoDAO.get(id);
//
//        if (produto != null) {
//
//            CursoDAO.remove(produto);
//
//            response.status(200); // success
//        	return id;
//        } else {
//            response.status(404); // 404 Not found
//            return "Produto não encontrado.";
//        }
//	}
//
	public Object getAll(Request request, Response response) {
		String returnValue = new String();
		int maxId = 1;
		returnValue += "{\"cursos\":[";
		for (Curso curso : CursoDAO.getAll()) {
			returnValue += ("{\"id\":" + curso.getId() + ",\"idioma\":\"" + curso.getIdioma() + "\",\"autor\":\"" + curso.getAutor() + "\",\"conteudo\":\"" + curso.getConteudo()
					 + "\",\"url\":\"" + curso.getURL() + "\",\"titulo\":\"" + curso.getTitulo() + "\",\"horas\":\"" + curso.getHoras() + "\",\"avaliacao\":\"" + curso.getAvaliacao()
					 + "\",\"areaid\":\"" + curso.getAreaId() + "\"}");
			if(maxId != CursoDAO.getMaxIdCurso()) {
				returnValue += ",";
			}
			maxId++;
		}
		returnValue += ("]}");
	    response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
	    return returnValue.toString();
	}
}