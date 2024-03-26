package com.algaworks.restaurante;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.restaurante.di.modelo.Cliente;
import com.algaworks.restaurante.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {
	
	private AtivacaoClienteService ativacaoClienteService;
	
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {

		this.ativacaoClienteService = ativacaoClienteService;
	}


	@GetMapping("/hello") //indica o endereço da página
	@ResponseBody //retorna o retorno da classe na página web
	public String hello() {
		
		Cliente joao = new Cliente("João", "joao@xyz.com", "455676");
		
		ativacaoClienteService.ativar(joao);
		return "Oi!";
		
		//ativei o devtools, para rodar novamente a classe é só aperta ctrl+s
		
	}

}
