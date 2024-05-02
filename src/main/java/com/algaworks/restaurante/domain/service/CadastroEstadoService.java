package com.algaworks.restaurante.domain.service;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.restaurante.domain.exception.EntidadeEmUsoException;
import com.algaworks.restaurante.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.restaurante.domain.model.Estado;
import com.algaworks.restaurante.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String MSG_ESTADO_EM_USO = "Não é possível excluir o Estado %d, pois está associado a uma cidade";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
		
	}
	
	public Estado salvar(Estado estado) {
			return estadoRepository.save(estado);
	}
	
	public Estado atualizar(Long estadoId, Estado estado) {
		Estado estadoAtual = buscarOuFalhar(estadoId);
		
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return estadoRepository.save(estadoAtual);
		
	}
	
	public void excluir(Long estadoId) {
		buscarOuFalhar(estadoId);
		
		try {
			estadoRepository.deleteById(estadoId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO,
					estadoId));
		}
	}
	
	

}
