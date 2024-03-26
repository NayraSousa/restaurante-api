package com.algaworks.restaurante.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.restaurante.di.modelo.Cliente;

//@Qualifier("email")
@Profile("prod")
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorEmail implements Notificador {
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s através do email %s: %s\n",
				cliente.getNome(), cliente.getEmail(),mensagem);
		}


	



}
