package br.com.alura.services.processadores.component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PedidoJson {

  private String categoria;
  private String produto;
  private BigDecimal preco;
  private int quantidade;
  private String data;
  private String cliente;

  public PedidoJson() {}

  public PedidoJson(
    String categoria,
    String produto,
    BigDecimal preco,
    int quantidade,
    String data,
    String cliente
  ) {
    this.categoria = categoria;
    this.produto = produto;
    this.preco = preco;
    this.quantidade = quantidade;
    this.data = data;
    this.cliente = cliente;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getProduto() {
    return produto;
  }

  public void setProduto(String produto) {
    this.produto = produto;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public LocalDate getData() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return LocalDate.parse(this.data, formatter);
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getCliente() {
    return cliente;
  }

  public void setCliente(String cliente) {
    this.cliente = cliente;
  }
}
