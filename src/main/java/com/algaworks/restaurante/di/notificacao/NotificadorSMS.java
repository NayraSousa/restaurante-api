package com.algaworks.restaurante.di.notificacao;

import org.springframework.stereotype.Component;

import com.algaworks.restaurante.di.modelo.Cliente;

//@Primary outra forma de resolver problema de ambiguidade
//a classe que tiver o @Primary é instanciada
@Component
public class NotificadorSMS implements Notificador {
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s através do telefone %s: %s\n",
				cliente.getNome(), cliente.getTelefone(),mensagem);
		}


}
