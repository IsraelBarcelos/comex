package br.com.alura.services.relatorios.categorias;

import br.com.alura.comex.Pedido;
import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;
import java.util.Comparator;

public class ProdutoMaisCaro implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.println(
      "\n#### RELATÓRIO DE PRODUTOS MAIS CAROS DE CADA CATEGORIA"
    );

    fechamento
      .getSortedCategoriasProcessadas()
      .stream()
      .forEach(
        categoria -> {
          System.out.println("\nCATEGORIA: " + categoria);
          Pedido pedidoPlaceholder = fechamento
            .getPedidosDeUmFechamento()
            .getPedidos()
            .stream()
            .filter(
              pedido -> pedido.getProduto().getCategoria().equals(categoria)
            )
            .max(Comparator.comparing(pedido -> pedido.getProduto().getPreco()))
            .get();
          System.out.println(
            "PRODUTO: " + pedidoPlaceholder.getProduto().getNome()
          );
          System.out.println(
            "PREÇO: R$ " + pedidoPlaceholder.getProduto().getPreco()
          );
        }
      );
  }
}
