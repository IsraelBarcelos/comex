package br.com.alura.comex.comercial.aplicacao.produto;

import br.com.alura.comex.comercial.dominio.categoria.Categoria;
import br.com.alura.comex.comercial.dominio.categoria.RepositorioDeCategoria;
import br.com.alura.comex.comercial.dominio.categoria.ValidaIdCategoria;
import br.com.alura.comex.comercial.dominio.produto.Produto;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class ProdutoForm {

  @NotNull
  @NotEmpty
  @Length(min = 2)
  private String nome;
  private String descricao;
  @NotNull
  @Positive
  private BigDecimal precoUnitario;
  @NotNull
  private Integer quantidadeEstoque;
  @NotNull
  @ValidaIdCategoria
  private Long categoriaId;

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

  public Long getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Long categoriaId) {
    this.categoriaId = categoriaId;
  }

  public Produto converter(RepositorioDeCategoria categoriaRepository) {

    Optional<Categoria> categoria = categoriaRepository.encontrarCategoriaPeloId(categoriaId);

    if (!categoria.isPresent()) {
      throw new IllegalArgumentException("Categoria não encontrada");
    }

    return new ProdutoBuilder()
        .comNome(nome)
        .comDescricao(descricao)
        .comPrecoUnitario(precoUnitario)
        .comQuantidadeEstoque(quantidadeEstoque)
        .comCategoria(categoria.get())
        .build();
  }
}
