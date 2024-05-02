package com.algaworks.restaurante.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.restaurante.domain.exception.EntidadeEmUsoException;
import com.algaworks.restaurante.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.restaurante.domain.exception.NegocioException;
import com.algaworks.restaurante.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.restaurante.domain.model.Cozinha;
import com.algaworks.restaurante.domain.model.Restaurante;
import com.algaworks.restaurante.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
		
	private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removida, pois está em uso";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));		
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		try {
			Cozinha cozinha = cadastroCozinha.buscarOuFalhar(restaurante.getCozinha().getId());
		
			restaurante.setCozinha(cozinha);
		
			return restauranteRepository.save(restaurante);
		} catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
		
		BeanUtils.copyProperties(restaurante, restauranteAtual,
				"id", "formasPagamento", "endereco", "dataCadastro", "produtos");
		
		return salvar(restauranteAtual);
		
	}
	
	public void excluir(Long restauranteId) {
		buscarOuFalhar(restauranteId);
		
		try {
			restauranteRepository.deleteById(restauranteId);
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
		}
	}
	
	

}
