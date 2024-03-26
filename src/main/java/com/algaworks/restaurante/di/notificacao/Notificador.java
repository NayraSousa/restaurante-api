package com.algaworks.restaurante.di.notificacao;

import com.algaworks.restaurante.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}