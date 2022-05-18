package br.com.alura.comex;

import java.math.BigDecimal;

public class Cliente {

  private String nome;
  private int numeroDePedidos;
  private BigDecimal totalDeCompra;

  public Cliente(String nome) {
    this.nome = nome;
    this.numeroDePedidos = 1;
    this.totalDeCompra = BigDecimal.ZERO;
  }

  public String getNome() {
    return nome;
  }

  public int getNumeroDePedidos() {
    return numeroDePedidos;
  }

  public void addPedido() {
    this.numeroDePedidos++;
  }

  public BigDecimal getTotalDeCompras() {
    return totalDeCompra;
  }

  public void adicionaNovaCompra(BigDecimal valor) {
    this.totalDeCompra = this.totalDeCompra.add(valor);
  }
}
