package br.com.alura.comex;

import java.math.BigDecimal;

public class Produto {

  private final String nome;
  private final String categoria;
  private final BigDecimal preco;

  public Produto(String nome, String categoria, BigDecimal preco) {
    this.nome = nome;
    this.categoria = categoria;
    this.preco = preco;
  }

  public String getNome() {
    return nome;
  }

  public String getCategoria() {
    return this.categoria;
  }

  public BigDecimal getPreco() {
    return this.preco;
  }
}
