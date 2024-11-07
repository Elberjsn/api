package com.atividade.manyToOne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade.manyToOne.model.Departamento;
import com.atividade.manyToOne.model.Produtos;
import com.atividade.manyToOne.repository.ProdutosRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Service
public class ProdutoService {

	@Autowired
	ProdutosRepository produtosRepository;

	@Autowired
	DepartamentoService departamentoService;

	public void novoProduto(Produtos prod) {
		Departamento dep = departamentoService.buscarPorNome(prod.getDepartamento_id().getNomeDepartamento());

		prod.setDepartamento_id(dep);

		try {
			
			produtosRepository.save(prod);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Produtos> buscarTodosProdutos() {
		List<Produtos> prd = produtosRepository.findAll();

		if (prd.isEmpty()) {
			return null;
		} else {
			return prd;
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

	public void alterarProduto(Produtos prod) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Produtos");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Produtos prd = em.find(Produtos.class, prod.getIdProdutos());

		prd.setNomeProduto(prod.getNomeProduto());
		prd.setQtdEstoque(prod.getQtdEstoque());
		prd.setValor(prod.getValor());
		em.merge(prd);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public void deletarProduto(Produtos prod) {
		produtosRepository.deleteById(prod.getIdProdutos());
	}

	public void deleteById(int id) {
		Produtos p = buscar(id);
		deletarProduto(p);

	}

}
