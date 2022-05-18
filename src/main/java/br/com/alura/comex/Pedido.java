package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

  private Produto produto;
  private Cliente cliente;
  private int quantidade;

  private LocalDate data;

  public Pedido(
    Produto produto,
    Cliente cliente,
    int quantidade,
    LocalDate data
  ) {
    this.produto = produto;
    this.cliente = cliente;
    this.quantidade = quantidade;
    this.data = data;
  }

  public Produto getProduto() {
    return this.produto;
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public LocalDate getData() {
    return data;
  }

  public BigDecimal getValorTotal() {
    return this.getProduto().getPreco().multiply(new BigDecimal(quantidade));
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
      this.getProduto().getCategoria() +
      '\'' +
      ", produto='" +
      this.getProduto().getNome() +
      '\'' +
      ", cliente='" +
      this.cliente.getNome() +
      '\'' +
      ", preco=" +
      this.getProduto().getPreco() +
      ", quantidade=" +
      quantidade +
      ", data=" +
      data +
      '}'
    );
  }
}
