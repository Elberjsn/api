package com.atividade.manyToOne.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Produtos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProdutos;

	@NotNull(message = "O Nome do Produto não pode ser Vazia")
	@NotBlank(message = "O Nome do Produto não pode ser Vazia")
	private String nomeProduto;

	@Min(0)
	private Integer qtdEstoque;

	@Min(0)
	@Column(name = "valor", columnDefinition = "Decimal(10,2)")
	private Double valor;

	@NotNull(message = "Escolha um departamento")
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "departamento_id")
	private Departamento departamento_id;

	public Produtos() {
	}

	public Produtos(Integer idProdutos,
			@NotBlank(message = "O Nome do Produto não pode ser Vazia") @NotNull(message = "O Nome do Produto não pode ser Vazia") @NotEmpty(message = "Insira o Nome do departamento") String nomeProduto,
			@Min(0) Integer qtdEstoque, @Min(0) Double valor, Departamento departamento_id) {
		this.idProdutos = idProdutos;
		this.nomeProduto = nomeProduto;
		this.qtdEstoque = qtdEstoque;
		this.valor = valor;
		this.departamento_id = departamento_id;
	}

	@Override
	public String toString() {
		return "Nome: " + nomeProduto + " - Estoque: " + qtdEstoque + " - Valor Unitario:" + valor + " - Departamento: "
				+ departamento_id.getNomeDepartamento();
	}

	public Integer getIdProdutos() {
		return idProdutos;
	}

	public void setIdProdutos(Integer idProdutos) {
		this.idProdutos = idProdutos;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Departamento getDepartamento_id() {
		return departamento_id;
	}

	public void setDepartamento_id(Departamento departamento_id) {
		this.departamento_id = departamento_id;
	}

}
