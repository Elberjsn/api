package com.atividade.manyToOne.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atividade.manyToOne.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {
	@Query("select p from Produtos p where p.nomeProduto LIKE %:nome% ")
	List<Produtos> buscarPorNome(@Param("nome") String nome);
}
