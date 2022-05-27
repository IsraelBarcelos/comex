package br.com.alura.services.relatorios.categorias;

import br.com.alura.comex.Pedido;
import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;
import java.math.BigDecimal;
import java.util.Comparator;

public class VendasPorCategorias implements ItemDeRelatorio {

  public void imprime(Fechamento fechamento) {
    fechamento
      .getSortedCategoriasProcessadas()
      .stream()
      .forEach(
        categoria -> {
          long quantidadeVendida = fechamento
            .getPedidosDeUmFechamento()
            .getPedidos()
            .stream()
            .filter(
              pedido -> pedido.getProduto().getCategoria().equals(categoria)
            )
            .map(Pedido::getQuantidade)
            .reduce(Integer::sum)
            .get();

          BigDecimal valorPorCategorias = fechamento
            .getPedidosDeUmFechamento()
            .getPedidos()
            .stream()
            .filter(
              pedido -> pedido.getProduto().getCategoria().equals(categoria)
            )
            .sorted(
              Comparator.comparing(pedido -> pedido.getProduto().getPreco())
            )
            .map(Pedido::getValorTotal)
            .reduce(BigDecimal::add)
            .get();
          System.out.println("\n#### VENDAS POR CATEGORIAS");
          System.out.println("\nCATEGORIA: " + categoria);
          System.out.println("QUANTIDADE VENDIDA: " + quantidadeVendida);
          System.out.println("MONTANTE: R$" + valorPorCategorias);
        }
      );
  }
}
