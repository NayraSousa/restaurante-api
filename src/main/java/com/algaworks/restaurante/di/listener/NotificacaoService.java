package com.algaworks.restaurante.di.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.restaurante.di.notificacao.NivelUrgencia;
import com.algaworks.restaurante.di.notificacao.Notificador;
import com.algaworks.restaurante.di.notificacao.TipoDoNotificador;
import com.algaworks.restaurante.di.service.ClienteAtivadoEvent;

@Component
public class NotificacaoService {
	
	@TipoDoNotificador(NivelUrgencia.URGENTE)
	@Autowired
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo");
	}

}
