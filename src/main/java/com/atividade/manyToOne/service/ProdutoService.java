package com.atividade.manyToOne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.atividade.manyToOne.model.Departamento;
import com.atividade.manyToOne.model.Produtos;
import com.atividade.manyToOne.repository.ProdutosRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutosRepository produtosRepository;

	@Autowired
	DepartamentoService departamentoService;

	public List<Produtos> todosOS;

	@CacheEvict(value = "todosprds")
	public void novoProduto(Produtos prod) {
		Departamento dep = departamentoService.buscarPorNome(prod.getDepartamento_id().getNomeDepartamento());

		prod.setDepartamento_id(dep);

		Double valor = (prod.getValor() * prod.getQtdEstoque()) + dep.getValorDep();
		dep.setValorDep(valor);

		departamentoService.addValor(dep);

		try {
			produtosRepository.save(prod);
			todosOS.add(prod);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Cacheable(value = "todosprds")
	public List<Produtos> buscarTodosProdutos() {
		todosOS = produtosRepository.findAll();

		if (todosOS.isEmpty()) {
			return null;
		} else {
			return todosOS;
		}

	}

	public Produtos buscarProdutoId(Produtos prod) {
		return produtosRepository.findById(prod.getIdProdutos()).get();
	}

	public Produtos buscar(int id) {
		return produtosRepository.findById(id).get();
	}

	public List<Produtos> buscarPorNome(String nome) {
		return produtosRepository.buscarPorNome(nome);
	}

	@CacheEvict(value = "todosprds")
	public void alterarProduto(Produtos prod) {
		Produtos newProd = buscarProdutoId(prod);

		newProd.setNomeProduto(prod.getNomeProduto());
		newProd.setQtdEstoque(prod.getQtdEstoque());
		newProd.setValor(prod.getValor());

		novoProduto(newProd);
	}

	@CacheEvict(value = "todosprds")
	public void deletarProduto(Produtos prod) {
		todosOS.remove(prod);
		produtosRepository.delete(prod);
	}

	public void deleteById(int id) {
		Produtos p = buscar(id);
		deletarProduto(p);

	}

}
