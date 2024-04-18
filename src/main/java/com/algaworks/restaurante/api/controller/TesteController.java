package com.algaworks.restaurante.api.controller;

import static com.algaworks.restaurante.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.restaurante.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.restaurante.domain.model.Restaurante;
import com.algaworks.restaurante.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/restaurante/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome,
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurante/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome){
		
		
		return restauranteRepository.findComFreteGratis(nome);
	}
}
