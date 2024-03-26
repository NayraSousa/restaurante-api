package com.algaworks.restaurante.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaworks.restaurante.di.modelo.Cliente;

@Component
public class AtivacaoClienteService {
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
	}
}
	
	
	
	//@Qualifier("email") outra forma de resolver problema de ambiguidade
	
//	@TipoDoNotificador(NivelUrgencia.URGENTE)
//	@Autowired
//	private Notificador notificador;
//	
//	public void ativar(Cliente cliente) {
//		cliente.ativar(cliente);
//			notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
//	}
	
	//@PostConstruct
//	public void init() {
//		System.out.println("INIT " +notificador);
//	}
//	
//	//@PreDestroy
//	public void destroy() {
//		System.out.println("DESTROY " +notificador);
//	}
	
	
//	@Autowired
//	// uma forma de resolver problema de ambiguidade
//	private List<Notificador> notificadores;
//	
//	public void ativar(Cliente cliente) {
//		cliente.ativar(cliente);
//		for(Notificador notificador: notificadores) {
//			notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
//		}
//	}
	
//	@Autowired //define como construtor padrão
//	public AtivacaoClienteService(Notificador notificador) {
//		this.notificador = notificador;
//		
//	}
//	public AtivacaoClienteService(String qualquer){
//}
	
//	@Autowired
//	public void setNotificador(Notificador notificador) {
//		this.notifica = notificador;
//	}


