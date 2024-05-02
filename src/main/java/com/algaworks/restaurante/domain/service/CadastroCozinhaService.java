package com.algaworks.restaurante.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.restaurante.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.restaurante.domain.exception.EntidadeEmUsoException;
import com.algaworks.restaurante.domain.model.Cozinha;
import com.algaworks.restaurante.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	
	public List<Cozinha> listar(){
		return cozinhaRepository.findAll();
		
	}
	
	public Cozinha buscarOuFalhar(Long cozinhaId) {
		
		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
						
	}
	
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
		
	}
	
	public Cozinha atualizar(Long cozinhaId, Cozinha cozinha) {
		Cozinha cozinhaAtual = buscarOuFalhar(cozinhaId);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		
		return cozinhaRepository.save(cozinhaAtual);
	}
	
	public void excluir(Long cozinhaId) {
		buscarOuFalhar(cozinhaId);

		try {
			cozinhaRepository.deleteById(cozinhaId);
			
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO,
					cozinhaId));
		}
		
	}
}
