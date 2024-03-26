package com.algaworks.restaurante.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.restaurante.di.modelo.Cliente;

@Profile("dev")	
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorEmailMock implements Notificador {
		
	public NotificadorEmailMock() {
		System.out.println("Notificador Email Mock");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("MOCK: Notificando %s através do email %s: %s\n",
				cliente.getNome(), cliente.getEmail(),mensagem);
			}


}
