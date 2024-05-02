package com.algaworks.restaurante.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.restaurante.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.restaurante.domain.exception.EntidadeEmUsoException;
import com.algaworks.restaurante.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.restaurante.domain.exception.NegocioException;
import com.algaworks.restaurante.domain.model.Cidade;
import com.algaworks.restaurante.domain.model.Estado;
import com.algaworks.restaurante.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	private static final String MSG_CIDADE_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}
	
	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
				
	}
	public Cidade salvar(Cidade cidade) {
		try {
			Estado estado = cadastroEstado.buscarOuFalhar(cidade.getEstado().getId());
		
			cidade.setEstado(estado);
		
			return cidadeRepository.save(cidade);
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	public Cidade atualizar(Long cidadeId, Cidade cidade) {
		Cidade cidadeAtual = buscarOuFalhar(cidadeId);
		
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		return salvar(cidadeAtual);
		
	}
	
	public void excluir(Long cidadeId) {
		buscarOuFalhar(cidadeId);
		
		try {
			cidadeRepository.deleteById(cidadeId);
			
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO,
					cidadeId));
		}
	}
	
	
	
}
