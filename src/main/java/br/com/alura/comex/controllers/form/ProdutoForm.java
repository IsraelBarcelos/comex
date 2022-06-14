package br.com.alura.comex.controllers.form;

import br.com.alura.comex.builders.ProdutoBuilder;
import br.com.alura.comex.models.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ProdutoForm {

  @NotNull
  @NotEmpty
  @Length(min = 3)
  private String nome;
  private String descricao;
  @NotNull
  @NotEmpty
  private BigDecimal precoUnitario;
  @NotNull
  private Integer quantidadeEstoque;
  @NotNull
  @NotEmpty
  private String nomeCategoria;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public void setPrecoUnitario(BigDecimal precoUnitario) {
    this.precoUnitario = precoUnitario;
  }

  public Integer getQuantidadeEstoque() {
    return quantidadeEstoque;
  }

  public void setQuantidadeEstoque(Integer quantidadeEstoque) {
    this.quantidadeEstoque = quantidadeEstoque;
  }

  public String getNomeCategoria() {
    return nomeCategoria;
  }

  public void setNomeCategoria(String nomeCategoria) {
    this.nomeCategoria = nomeCategoria;
  }

  public Produto converter(CategoriaRepository categoriaRepository) {
    Produto produto = new ProdutoBuilder()
        .comNome(nome)
        .comDescricao(descricao)
        .comPrecoUnitario(precoUnitario)
        .comQuantidadeEstoque(quantidadeEstoque)
        .comCategoria(categoriaRepository.findByNome(nomeCategoria))
        .build();

    return produto;
  }
}
