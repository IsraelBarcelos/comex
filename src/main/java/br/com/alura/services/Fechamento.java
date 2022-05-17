package br.com.alura.services;

import br.com.alura.comex.CategoriasProcessadas;
import br.com.alura.comex.Cliente;
import br.com.alura.comex.ClientesDoSistema;
import br.com.alura.comex.Pedido;
import br.com.alura.comex.PedidosDeUmFechamento;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class Fechamento {

  CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas();
  PedidosDeUmFechamento pedidosDeUmFechamento;
  ClientesDoSistema clientesDoSistema;

  public Fechamento(
    PedidosDeUmFechamento pedidosDeUmFechamento,
    ClientesDoSistema clientesDoSistema
  ) {
    this.pedidosDeUmFechamento = pedidosDeUmFechamento;
    this.clientesDoSistema = clientesDoSistema;

    verificaSeEstaNaCategoria();

    new RelatorioSintetico(this);
  }

  public Pedido getPedidoMaisBarato() throws IllegalStateException {
    Optional<Pedido> pedidoMaisBarato =
      this.pedidosDeUmFechamento.getPedidos()
        .stream()
        .max(Comparator.comparing(Pedido::getPreco));
    if (pedidoMaisBarato.isPresent()) return pedidoMaisBarato.get();
    throw new IllegalStateException("Não existe pedido mais caro");
  }

  public Pedido getPedidoMaisCaro() throws IllegalStateException {
    Optional<Pedido> pedidoMaisCaro =
      this.pedidosDeUmFechamento.getPedidos()
        .stream()
        .max(Comparator.comparing(Pedido::getPreco));
    if (pedidoMaisCaro.isPresent()) return pedidoMaisCaro.get();
    throw new IllegalStateException("Não existe pedido mais caro");
  }

  public BigDecimal getMontanteDeVendas() {
    return this.pedidosDeUmFechamento.getPedidos()
      .stream()
      .map(Pedido::getValorTotal)
      .reduce(BigDecimal::add)
      .get();
  }

  public int getTotalDePedidosRealizados() {
    return this.pedidosDeUmFechamento.getPedidos().size();
  }

  public int getTotalDeProdutosVendidos() {
    return this.pedidosDeUmFechamento.getPedidos()
      .stream()
      .map(Pedido::getQuantidade)
      .reduce(Integer::sum)
      .get();
  }

  public int totalDethisPedidosRealizados() {
    return this.pedidosDeUmFechamento.getPedidos().size();
  }

  public void verificaSeEstaNaCategoria() {
    this.pedidosDeUmFechamento.getPedidos()
      .stream()
      .forEach(
        pedido -> {
          if (!this.categoriasProcessadas.contains(pedido.getCategoria())) {
            this.categoriasProcessadas.add(pedido.getCategoria());
          }
        }
      );
  }

  public int getTotalDeCategorias() {
    return categoriasProcessadas.getTotalDeCategorias();
  }

  public Collection<Cliente> getClientesFieis() {
    return this.clientesDoSistema.getClientesDoSistema();
  }
}
