package com.atividade.manyToOne.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atividade.manyToOne.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
	
	@Query("select d from Departamento d where d.nomeDepartamento LIKE %:nome%")
	List<Departamento> buscarPorNome(@Param("nome")String nome);
	
}
	