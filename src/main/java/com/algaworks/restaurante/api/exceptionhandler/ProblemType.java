package com.algaworks.restaurante.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	PARAMETRO_INVALIDO("parametro_invalido", "Parâmetro inválido"),
	CAMPO_INVALIDO("/campo-invalido", "Campo inválido"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	DADOS_INVALIDOS("/campos-invalidos", "Dados inválidos");
	
	private String titulo;
	private String uri;
	
	ProblemType(String path, String titulo) {
		this.uri = "https://restaurante.com.br" + path;
		this.titulo = titulo;
	}

}
