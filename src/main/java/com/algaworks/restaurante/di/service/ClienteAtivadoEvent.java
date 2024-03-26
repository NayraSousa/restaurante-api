package com.algaworks.restaurante.di.service;

import com.algaworks.restaurante.di.modelo.Cliente;

public class ClienteAtivadoEvent {
	
	private Cliente cliente;
	
	public ClienteAtivadoEvent(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

}
