package br.com.alura.comex;

import java.util.ArrayList;
import java.util.Collection;

public class PedidosDeUmFechamento {

  ArrayList<Pedido> pedidos;

  public PedidosDeUmFechamento(Collection<Pedido> pedidos) {
    this.pedidos = new ArrayList<>(pedidos);
  }

  public ArrayList<Pedido> getPedidos() {
    return pedidos;
  }

  public void add(Pedido pedido) {
    pedidos.add(pedido);
  }
}
