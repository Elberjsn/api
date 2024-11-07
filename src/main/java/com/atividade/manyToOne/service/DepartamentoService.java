package com.atividade.manyToOne.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.atividade.manyToOne.model.Departamento;
import com.atividade.manyToOne.repository.DepartamentoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


@Service
public class DepartamentoService {

	@Autowired
	DepartamentoRepository departamentoRepository;

	@Autowired
	CacheManager cacheManager;

	public void salvarDepartamento(Departamento dep) {
		dep.setValorDep(0.0);
		departamentoRepository.save(dep);

	}

	public List<Departamento> buscarDepartamentos() {
	
		return departamentoRepository.findAll();

	}

	public Departamento buscarDepartamentoId(int id) {
		Optional<Departamento> dep = departamentoRepository.findById(id);
		System.out.println(id);
		if (dep.isPresent()) {
			System.out.println(dep.get());
			return dep.get();
		} else {
			return null;
		}

	}

	public List<Departamento> buscarPorNome(String nome) {
		return departamentoRepository.buscarPorNome(nome);
	}

	public void deletarDepartamento(Departamento dep) {
		
		departamentoRepository.delete(dep);
	}
	
	public void alterarDepartamento(Departamento newdep) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Departamento");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Departamento dep = em.find(Departamento.class,newdep.getIdDepartamento());

		dep.setNomeDepartamento(newdep.getNomeDepartamento());
		em.merge(dep);
		em.getTransaction().commit();
		em.close();
		emf.close();
		Departamento newDep = buscarDepartamentoId(dep.getIdDepartamento());

		newDep.setNomeDepartamento(dep.getNomeDepartamento());
		newDep.setValorDep(dep.getValorDep());
		departamentoRepository.save(newDep);
		
	}

	
	

}
