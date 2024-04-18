package com.algaworks.restaurante.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.restaurante.domain.execption.EntidadeNaoEncontradaException;
import com.algaworks.restaurante.domain.model.Cidade;
import com.algaworks.restaurante.domain.model.Estado;
import com.algaworks.restaurante.domain.repository.CidadeRepository;
import com.algaworks.restaurante.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException
						(String.format("Estado do código %d não existe", estadoId)));
		
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
		
	}
	
	public void excluir(Cidade cidade) {
		try {
			cidadeRepository.deleteById(cidade.getId());
			
		} catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cidade com o código %d", 
					cidade.getId()));
		}
	}
	
	
	
}
