//package com.algaworks.restaurante;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.algaworks.restaurante.di.notificacao.NotificadorEmail;
//import com.algaworks.restaurante.di.service.AtivacaoClienteService;
//
////@Configuration //definição de beans
//public class RestauranteConfig {
//	
//	@Bean
//	public NotificadorEmail notificadorEmail() {
//		NotificadorEmail notificador = new NotificadorEmail("nayra@hndf.com");
//		notificador.setCaixaAlta(true);
//		
//		return notificador;
//		
//	}
//	
//	@Bean
//	public AtivacaoClienteService ativacao() {
//		return new AtivacaoClienteService(notificadorEmail());
//	}
//
//}
