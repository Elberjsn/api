package com.atividade.manyToOne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atividade.manyToOne.model.Produtos;
import com.atividade.manyToOne.service.DepartamentoService;
import com.atividade.manyToOne.service.ProdutoService;

import jakarta.validation.Valid;

@Controller
public class ControllerProduto {
	@Autowired
	ProdutoService produtoService;

	@Autowired
	DepartamentoService departamentoService;

	@GetMapping("/")
	public String produtos(Model model) {

		model.addAttribute("title", "Produtos");
		model.addAttribute("deps", departamentoService.buscarDepartamentos());
		model.addAttribute("produtos",  produtoService.buscarTodosProdutos());
		model.addAttribute("prd", new Produtos());

		return "index";

	}

	@PostMapping("/")
	public String newProduto(@Valid @ModelAttribute("prd") Produtos prod, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("deps", departamentoService.buscarDepartamentos());
			model.addAttribute("produtos", produtoService.buscarTodosProdutos());
			model.addAttribute("title", "Produtos");
			return "index";
		}
		produtoService.novoProduto(prod);
		return "redirect:/";
	}

	@PostMapping("/editProd")
	public String alterarProduto(@Valid @ModelAttribute("produto") Produtos prod, Model model) {
		produtoService.novoProduto(prod);
		return "redirect:/";
	}

	@GetMapping("/deleteProd/{id}")
	public String deleteProd(@PathVariable("id") int idpr, Model model) {
		produtoService.deleteById(idpr);
		return "redirect:/";
	}

	@GetMapping("/buscar")
	public String buscarPrd(@RequestParam(name = "nameprd") String nome, Model model) {
		
		
		if (nome.equals("none")) {
			model.addAttribute("produtos", produtoService.buscarTodosProdutos());
		}else{
			model.addAttribute("produtos", produtoService.buscarPorNome(nome));
		}

		return "fragments :: tb_Prd";
	}

	@GetMapping("/img")
	public String img(Model model){
		model.addAttribute("title", "Diagrama");

		return "img";
	}

	@GetMapping("/error")
	public String erro(Model model){
		return "redirect:/";
	}
}
