package com.algaworks.restaurante.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.restaurante.domain.model.FormaDePagamento;

@Repository
public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento, Long>{
 
}
