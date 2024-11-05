package com.atividade.manyToOne.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.atividade.manyToOne.model.Departamento;
import com.atividade.manyToOne.repository.DepartamentoRepository;


@Service
public class DepartamentoService {

	@Autowired
	DepartamentoRepository departamentoRepository;

	@Autowired
	CacheManager cacheManager;

	@CacheEvict(value = "todosDeps", allEntries = true)
	public void salvarDepartamento(Departamento dep) {
		dep.setValorDep(0.0);
		departamentoRepository.save(dep);

	}

	@Cacheable(value = "todosDeps")
	public List<Departamento> buscarDepartamentos() {
	
		return departamentoRepository.findAll();

	}

	public Departamento buscarDepartamentoId(int id) {
		Optional<Departamento> dep = departamentoRepository.findById(id);

		if (dep.isPresent()) {
			return dep.get();
		} else {
			return null;
		}

	}

	public Departamento buscarPorNome(String nome) {
		return departamentoRepository.buscarPorNome(nome).get(0);
	}

	@CacheEvict(value = "todosDeps", allEntries = true)
	public void deletarDepartamento(Departamento dep) {
		
		departamentoRepository.delete(dep);
	}
	
	@CacheEvict(value = "todosDeps", allEntries = true)
	public void alterarDepartamento(Departamento dep) {
		Departamento newDep = buscarDepartamentoId(dep.getIdDepartamento());

		newDep.setNomeDepartamento(dep.getNomeDepartamento());
		departamentoRepository.save(newDep);
		
	}

	public void addValor(Departamento dep) {
		departamentoRepository.save(dep);
	}

	

}
