package com.algaworks.restaurante.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.restaurante.domain.execption.EntidadeEmUsoException;
import com.algaworks.restaurante.domain.execption.EntidadeNaoEncontradaException;
import com.algaworks.restaurante.domain.model.Estado;
import com.algaworks.restaurante.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		
		return estadoRepository.save(estado);
	}
	
	public void excluir(Estado estado) {
		try {
			estadoRepository.deleteById(estado.getId());
			
		} catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Estado com esse códido %d",
					estado.getId()));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Não é possível excluir o Estado %d, pois está associado a uma cidade",
					estado.getId()));
		}
	}
	
	

}
