package com.algaworks.restaurante.infrastructure.repository;

import static com.algaworks.restaurante.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.restaurante.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.restaurante.domain.model.Restaurante;
import com.algaworks.restaurante.domain.repository.RestauranteRepository;
import com.algaworks.restaurante.domain.repository.RestauranteRepositoryQueries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
		}
		
		if(taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}
		
		if(taxaFreteFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		
		return query.getResultList();

	}

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}

}


//var jpql = new StringBuilder();
//jpql.append("from Restaurante where 0 = 0");
//
//var parametros = new HashMap<String, Object>();
//
//if(StringUtils.hasLength(nome)) {
//	jpql.append("and nome like :nome ");
//	parametros.put("nome", "%" + nome + "%");
//	
//}
//
//if(taxaFreteInicial != null) {
//	jpql.append("and taxaFrete >= :taxaInicial ");
//	parametros.put("taxaInicial", taxaFreteInicial);
//}
//
//if(taxaFreteFinal != null) {
//	jpql.append("and taxaFrete <= :taxaFinal ");
//	parametros.put("taxaFinal", taxaFreteFinal);
//}
//
//TypedQuery<Restaurante> query =  manager.createQuery(jpql.toString(), Restaurante.class);
//
//parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
//
//return query.getResultList();
