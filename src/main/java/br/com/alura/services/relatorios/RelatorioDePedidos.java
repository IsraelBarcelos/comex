package br.com.alura.services.relatorios;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.pedidos.PedidoMaisBarato;
import br.com.alura.services.relatorios.pedidos.PedidoMaisCaro;
import br.com.alura.services.relatorios.pedidos.ProdutoMaisVendidoComLimit;
import br.com.alura.services.relatorios.pedidos.TotalDePedidos;
import br.com.alura.services.relatorios.pedidos.TotalDeProdutosVendidos;
import br.com.alura.services.relatorios.pedidos.TotalDeVendas;
import java.util.Set;

public class RelatorioDePedidos extends Relatorio {

  public RelatorioDePedidos(Fechamento fechamento) {
    super(fechamento);
  }

  @Override
  public void imprimirRelatorios() {
    final Set<ItemDeRelatorio> itens = Set.of(
      new PedidoMaisBarato(),
      new PedidoMaisCaro(),
      new ProdutoMaisVendidoComLimit(),
      new TotalDePedidos(),
      new TotalDeProdutosVendidos(),
      new TotalDeVendas()
    );

    itens.forEach(item -> item.imprime(this.fechamento));
  }

  public String toString() {
    return "Relatorio de pedidos aqui.";
  }
}
