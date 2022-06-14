package br.com.alura.comex.builders;

import br.com.alura.comex.models.Cliente;
import br.com.alura.comex.models.ItemPedido;
import br.com.alura.comex.models.Pedido;
import br.com.alura.comex.models.TipoDescontoPedido;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoBuilder {

  private Pedido pedido;

  public PedidoBuilder() {
    this.pedido = new Pedido();
  }

  public PedidoBuilder comData(LocalDate data) {
    this.pedido.setData(data);
    return this;
  }

  public PedidoBuilder comCliente(Cliente cliente) {
    this.pedido.setCliente(cliente);
    return this;
  }

  public PedidoBuilder comDesconto(BigDecimal desconto) {
    this.pedido.setDesconto(desconto);
    return this;
  }

  public PedidoBuilder comTipoDescontoPedido(
    TipoDescontoPedido tipoDescontoPedido
  ) {
    this.pedido.setTipoDesconto(tipoDescontoPedido);
    return this;
  }

  public PedidoBuilder comItens(List<ItemPedido> itens) {
    itens.forEach(this.pedido::adicionarItemPedido);
    return this;
  }

  public Pedido build() {
    return this.pedido;
  }
}
