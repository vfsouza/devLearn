$("#form-add-curso").submit(function (event) {
	event.preventDefault();

	var titulo = $("#inputTitulo").val();
	var area = $("#inputArea").val();
	var autor = $("#inputNome").val();
	var idioma = $("#inputIdioma").val();
	var conteudo = $("#inputConteudo").val();
	var url = $("#inputUrl").val();

	$.ajax({
		url: "http://localhost:6789/cadastrar/curso",
		type: "POST",
		data: {
			idioma: idioma,
			titulo: titulo,
			area: area,
			autor: autor,
			conteudo: conteudo,
			url: url,
		},
	}).done(function (data) {
		$("#inputIdCurso").attr("value", `${data}`);
		window.location.reload();
	});
});

function removeCurso(id) {
	if (confirm("Deseja realmente remover o curso?")) {
		$.get(`http://localhost:6789/cursos/delete/${id}`, function (data) {
			console.log(data);
		});
	}
}

function submitModulo(num) {
	let titulo = $("#modTitulo").val();
	let tituloMod = $("#inputTituloMod").val();
	let idCurso = $("#inputIdCurso").val();
	let horas = 0;
	if (num == 1) {
		horas = $("#inputHorasMod").val();
	}
	let topico = $("#inputTopicoMod").val();
	$.ajax({
		url: "http://localhost:6789/cadastrar/modulo",
		type: "GET",
		data: {
			idCurso: idCurso,
			titulo: tituloMod,
			horas: horas,
			topico: topico,
		},
	}).done(function (idModulo) {
		console.log(num);
		if (num == 1) {
			let url = $("#modURL").val();
			let descricao = $("#modDescricao").val();
			console.log(url);
			$.ajax({
				url: "http://localhost:6789/cadastrar/video",
				type: "GET",
				data: {
					idModulo: idModulo.id,
					idCurso: idCurso,
					titulo: titulo,
					url: url,
					horas: horas,
					descricao: descricao,
				},
			});
		} else {
			let questao = $("#modQuestao").val();
			$.ajax({
				url: "http://localhost:6789/cadastrar/desafio",
				type: "GET",
				data: {
					idModulo: idModulo.id,
					idCurso: idCurso,
					titulo: titulo,
					questao: questao,
				},
			});
		}
	});
}
