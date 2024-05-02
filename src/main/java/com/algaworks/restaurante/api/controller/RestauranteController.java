package com.algaworks.restaurante.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.restaurante.Groups.CozinhaId;
import com.algaworks.restaurante.domain.model.Restaurante;
import com.algaworks.restaurante.domain.service.CadastroRestauranteService;

import jakarta.validation.Valid;

@RequestMapping("/restaurante")
@RestController
public class RestauranteController {
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	
	@GetMapping
	public List<Restaurante> listar(){
		return cadastroRestaurante.listar();
	}
	
	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId){
		return cadastroRestaurante.buscarOuFalhar(restauranteId);
	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@Valid @RequestBody Restaurante restaurante){
		return cadastroRestaurante.salvar(restaurante);
	}
	
	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(
			@PathVariable Long restauranteId, @Valid @RequestBody Restaurante restaurante){
		return cadastroRestaurante.atualizar(restauranteId, restaurante);
		
	}
	
	@DeleteMapping("{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId) {
		cadastroRestaurante.excluir(restauranteId);
	}
	
	
	
}
	
//	@PatchMapping("/{restauranteId}")
//	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, 
//			@RequestBody Map<String, Object> campos){
//		
//		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
//		
//		if(restauranteAtual.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		merge(campos, restauranteAtual.get());
//		
//		return atualizar(restauranteId, restauranteAtual.get());
//		}
//	
//	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
//		
//		camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
//			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
//			field.setAccessible(true);
//			
//			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//			
////			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
//			
//			ReflectionUtils.setField(field, restauranteDestino, novoValor);
//		});
//	}
		

		
	
