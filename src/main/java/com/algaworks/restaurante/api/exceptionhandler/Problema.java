package com.algaworks.restaurante.api.exceptionhandler;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problema {
	private Integer status;
	private String tipo;
	private String titulo;
	private String detalhe;
	private List<Field> fields;
	
	@Getter
	@Builder
	public static class Field{
		
		private String nome;
		private String mensagem;
	}
	
}
