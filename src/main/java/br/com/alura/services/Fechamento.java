package br.com.alura.services;

import br.com.alura.comex.CategoriasProcessadas;
import br.com.alura.comex.Cliente;
import br.com.alura.comex.ClientesDoSistema;
import br.com.alura.comex.Pedido;
import br.com.alura.comex.PedidosDeUmFechamento;
import br.com.alura.services.processadores.Processador;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Fechamento {

  private Processador processador;
  private final CategoriasProcessadas categoriasProcessadas;
  private final PedidosDeUmFechamento pedidosDeUmFechamento;
  private final ClientesDoSistema clientesDoSistema;

  public Fechamento(Processador processador)
    throws IOException, URISyntaxException {
    this.processador = processador;
    this.processador.execute();
    this.pedidosDeUmFechamento =
      new PedidosDeUmFechamento(processador.getPedidos());
    this.clientesDoSistema = this.processador.getClientesDoSistema();
    this.categoriasProcessadas =
      new CategoriasProcessadas(this.pedidosDeUmFechamento.getPedidos());
  }

  public PedidosDeUmFechamento getPedidosDeUmFechamento() {
    return pedidosDeUmFechamento;
  }

  public Pedido getPedidoMaisBarato() throws IllegalStateException {
    Optional<Pedido> pedidoMaisBarato =
      this.pedidosDeUmFechamento.getPedidos()
        .stream()
        .min(Comparator.comparing(Pedido::getValorTotal));
    if (pedidoMaisBarato.isPresent()) return pedidoMaisBarato.get();
    throw new IllegalStateException("Não existe pedido mais caro");
  }

  public Pedido getPedidoMaisCaro() throws IllegalStateException {
    Optional<Pedido> pedidoMaisCaro =
      this.pedidosDeUmFechamento.getPedidos()
        .stream()
        .max(Comparator.comparing(Pedido::getValorTotal));
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

  public int getTotalDeCategorias() {
    return categoriasProcessadas.getTotalDeCategorias();
  }

  public Collection<Cliente> getClientesFieis() {
    return this.clientesDoSistema.getClientesDoSistema();
  }

  public Collection<String> getCategoriasProcessadas() {
    return this.categoriasProcessadas.getCategorias();
  }

  public Collection<String> getSortedCategoriasProcessadas() {
    return this.categoriasProcessadas.getSortedCategorias();
  }

  public List<Pedido> getProdutosMaisVendidoComSkips(int limit) {
    if (limit > 0) {
      return this.pedidosDeUmFechamento.getPedidos()
        .stream()
        .sorted(Comparator.comparing(Pedido::getQuantidade).reversed())
        .limit(3)
        .toList();
    }

    return this.pedidosDeUmFechamento.getPedidos()
      .stream()
      .sorted(Comparator.comparing(Pedido::getQuantidade).reversed())
      .toList();
  }

  public List<Cliente> getClientesMaisLucrativos(int limit) {
    if (limit > 0) {
      return this.clientesDoSistema.getClientesDoSistema()
        .stream()
        .sorted(Comparator.comparing(Cliente::getTotalDeCompras).reversed())
        .limit(limit)
        .toList();
    }

    return this.clientesDoSistema.getClientesDoSistema()
      .stream()
      .sorted(Comparator.comparing(Cliente::getTotalDeCompras).reversed())
      .toList();
  }
}
