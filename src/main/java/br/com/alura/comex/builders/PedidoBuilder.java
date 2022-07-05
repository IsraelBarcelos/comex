package br.com.alura.comex.builders;

import br.com.alura.comex.models.Cliente;
import br.com.alura.comex.models.ItemPedido;
import br.com.alura.comex.models.Pedido;
import br.com.alura.comex.models.descontos.descontoPedido.TipoDescontoPedido;
import br.com.alura.comex.repository.PedidoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoBuilder {

  private Pedido pedido;

  public PedidoBuilder() {
    this.pedido = new Pedido();
  }

  public PedidoBuilder comData(LocalDateTime data) {
    this.pedido.setData(data);
    return this;
  }

  public PedidoBuilder comCliente(Cliente cliente) {
    this.pedido.setCliente(cliente);
    return this;
  }

  public PedidoBuilder comDesconto(PedidoRepository pedidoRepository) {
    this.pedido.setDesconto(pedidoRepository);
    return this;
  }

  public PedidoBuilder comTipoDescontoPedido(
      TipoDescontoPedido tipoDescontoPedido) {
    this.pedido.setTipoDesconto(tipoDescontoPedido);
    return this;
  }

  public PedidoBuilder comItens(List<ItemPedido> itens) {
    itens.forEach(item -> {
      this.pedido.adicionarItemPedido(item);
    });
    return this;
  }

  public Pedido build() {
    return this.pedido;
  }
}
