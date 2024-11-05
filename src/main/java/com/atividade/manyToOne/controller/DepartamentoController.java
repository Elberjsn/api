package com.atividade.manyToOne.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atividade.manyToOne.model.Departamento;
import com.atividade.manyToOne.service.DepartamentoService;

import jakarta.validation.Valid;

@Controller
public class DepartamentoController {

	@Autowired
	DepartamentoService departamentoService;

	List<Departamento> dps;
	
	
	@GetMapping("/dep")
	public String departamentoIndex(Model model) {

		this.dps = departamentoService.buscarDepartamentos();
		
		model.addAttribute("title", "Departamentos");

		model.addAttribute("todosDep", this.dps);
		
		Departamento dep = new Departamento();
		dep.setIdDepartamento(0);
		model.addAttribute("departamento",dep);
		return "departamentos";
	}
	
	@PostMapping("/dep")
	public String newDepartamento(@Valid @ModelAttribute("departamento") Departamento departamento, BindingResult result, Model model) {
		
		model.addAttribute("todosDep", this.dps);

		if (result.hasErrors()) {
			model.addAttribute("title", "Departamentos");
			return "departamentos";
		}
		departamentoService.salvarDepartamento(departamento);	
		model.addAttribute("todosDep", departamentoService.buscarDepartamentos());
		return "redirect:/dep";
	}
	
	@PostMapping("/alterDep/{id}")
	public String alterDepartamento(@Valid @ModelAttribute("departamento") Departamento dep,@PathVariable("id") int id, BindingResult result, Model model) {
		dep.setIdDepartamento(id);
		departamentoService.alterarDepartamento(dep);
		return "redirect:/";
	}
	
	@GetMapping("/deletarDep/{id}")
	public String deletarDepartamento(@PathVariable("id") int id, Model model) {
		Departamento del = departamentoService.buscarDepartamentoId(id);
		departamentoService.deletarDepartamento(del);
		model.addAttribute("title", "Departamentos");
		return "redirect:/dep";
	}

	@GetMapping("/buscardep")
	public String buscarDepartamento(@RequestParam(name = "namedep") String nome,Model model){

		model.addAttribute("todosDep",departamentoService.buscarPorNome(nome));


		return "fragments :: tb_dep";
	}
}
