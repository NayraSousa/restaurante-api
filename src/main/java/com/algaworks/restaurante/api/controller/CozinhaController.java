package com.algaworks.restaurante.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.restaurante.domain.model.Cozinha;
import com.algaworks.restaurante.domain.service.CadastroCozinhaService;

import jakarta.validation.Valid;
//
//@Controller
//@ResponseBody
@RequestMapping("/cozinhas")
@RestController // tem o @Controller e o @ResponseBody dentro dela
public class CozinhaController {
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	
	@GetMapping
	public List<Cozinha> listar(){
			return cadastroCozinha.listar();
	}
	
	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable("cozinhaId") Long cozinhaId) {
		return cadastroCozinha.buscarOuFalhar(cozinhaId);

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@Valid @RequestBody Cozinha cozinha){
		return cadastroCozinha.salvar(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@PathVariable Long cozinhaId, @Valid @RequestBody Cozinha cozinha){
		return cadastroCozinha.atualizar(cozinhaId, cozinha);
	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId){
		cadastroCozinha.excluir(cozinhaId);
		
		
	}

}
