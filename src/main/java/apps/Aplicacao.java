package apps;

import static spark.Spark.*;
import spark.Filter;

import services.CursoService;
import services.DesafioService;
import services.ModuloService;
import services.UsuarioService;
import services.VideoService;

public class Aplicacao {
	
	private static CursoService cursoService = new CursoService();
	private static UsuarioService usuarioService = new UsuarioService();
	private static ModuloService moduloService = new ModuloService();
	private static VideoService videoService = new VideoService();
	private static DesafioService desafioService = new DesafioService();
	
    public static void main(String[] args) {
        port(6789);
        
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Methods", "GET, POST");
        });
        
        post("/cadastrar/curso", (request, response) -> cursoService.add(request, response));
        
        post("/cadastrar/usuario", (request, response) -> usuarioService.add(request, response));
        
        get("/cadastrar/modulo", (request, response) -> moduloService.add(request, response));
        
        get("/cadastrar/video", (request, response) -> videoService.add(request, response));
        
        get("/cadastrar/desafio", (request, response) -> desafioService.add(request, response));
        
        post("/login", (request, response) -> usuarioService.login(request, response));
        
        get("/cursos", (request, response) -> cursoService.getAll(request, response));
        
        

//        get("/produto/:id", (request, response) -> produtoService.get(request, response));
//
//        get("/produto/update/:id", (request, response) -> produtoService.update(request, response));
//
//        get("/cursos/delete/:id", (request, response) -> cursoService.remove(request, response));
//
               
    }
}