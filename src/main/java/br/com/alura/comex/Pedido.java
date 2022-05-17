package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

  private String categoria;
  private String produto;
  private Cliente cliente;

  private BigDecimal preco;
  private int quantidade;

  private LocalDate data;

  public Pedido(
    String categoria,
    String produto,
    Cliente cliente,
    BigDecimal preco,
    int quantidade,
    LocalDate data
  ) {
    this.categoria = categoria;
    this.produto = produto;
    this.cliente = cliente;
    this.preco = preco;
    this.quantidade = quantidade;
    this.data = data;
  }

  public String getCategoria() {
    return categoria;
  }

  public String getProduto() {
    return produto;
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public LocalDate getData() {
    return data;
  }

  public BigDecimal getValorTotal() {
    return preco.multiply(new BigDecimal(quantidade));
  }

  public boolean isMaisBaratoQue(Pedido outroPedido) {
    return this.getValorTotal().compareTo(outroPedido.getValorTotal()) < 0;
  }

  public boolean isMaisCaroQue(Pedido outroPedido) {
    return this.getValorTotal().compareTo(outroPedido.getValorTotal()) > 0;
  }

  @Override
  public String toString() {
    return (
      "Pedido{" +
      "categoria='" +
      categoria +
      '\'' +
      ", produto='" +
      produto +
      '\'' +
      ", cliente='" +
      cliente +
      '\'' +
      ", preco=" +
      preco +
      ", quantidade=" +
      quantidade +
      ", data=" +
      data +
      '}'
    );
  }
}
