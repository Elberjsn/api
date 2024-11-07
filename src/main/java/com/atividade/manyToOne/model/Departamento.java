package com.atividade.manyToOne.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDepartamento")
	private Integer idDepartamento;

	@Column(name = "valorDep")
	private Double valorDep;

	@NotNull(message = "Preencha o Nome do departamento")
	@NotBlank(message = "O Nome do departamento não pode ser vazio!")
	@Column(name = "nomeDepartamento", nullable = false, unique = true)
	private String nomeDepartamento;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departamento_id", cascade = CascadeType.ALL)
	private List<Produtos> produtos;

	public Departamento() {
		this.valorDep = 0.0;
	}

	public Departamento(Double valorDep,
			@NotNull(message = "Preencha o Nome do departamento") @NotBlank(message = "O Nome do departamento não pode ser vazio!") String nomeDepartamento,
			List<Produtos> produtos) {
		this.valorDep = valorDep;
		this.nomeDepartamento = nomeDepartamento;
		this.produtos = produtos;
	}

	public Departamento(@NotNull(message = "O Nome do departamento não pode ser vazio!") String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}

	@Override
	public String toString() {
		return idDepartamento + " - " + produtos.toString();
	}

	public Double getValorDep() {
		return valorDep;
	}

	public void setValorDep(Double valorDep) {
		this.valorDep = valorDep;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}

	public List<Produtos> getProdutos() {
		return produtos;
	}

}
