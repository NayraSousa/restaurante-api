package com.algaworks.restaurante.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.restaurante.domain.execption.EntidadeEmUsoException;
import com.algaworks.restaurante.domain.execption.EntidadeNaoEncontradaException;
import com.algaworks.restaurante.domain.model.Estado;
import com.algaworks.restaurante.domain.repository.EstadoRepository;
import com.algaworks.restaurante.domain.service.CadastroEstadoService;

@RequestMapping("/estados")
@RestController
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable("estadoId") Long estadoId){
		
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if(estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstado.salvar(estado);
		
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
		Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);
		
		if(estadoAtual.isPresent()) {
			
			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
			Estado estadoSalvo = estadoRepository.save(estadoAtual.get());
			
			return ResponseEntity.ok(estadoSalvo);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId){
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if(estado.isPresent()) {
			try {
				cadastroEstado.excluir(estado.get());
				return ResponseEntity.noContent().build();
			
			}catch(EntidadeEmUsoException e){
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}		
	}
		return ResponseEntity.notFound().build();

	}
}
