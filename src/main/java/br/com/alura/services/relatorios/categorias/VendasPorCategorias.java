package br.com.alura.services.relatorios.categorias;

import br.com.alura.comex.Pedido;
import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Locale;

public class VendasPorCategorias implements ItemDeRelatorio {

  public void imprime(Fechamento fechamento) {
    System.out.println("\n#### VENDAS POR CATEGORIAS\n\n");
    System.out.println(geraSaida(fechamento));
  }

  public String geraSaida(Fechamento fechamento) {
    StringBuilder impressao = new StringBuilder();

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

          impressao.append("CATEGORIA: " + categoria);
          impressao.append("\nQUANTIDADE VENDIDA: " + quantidadeVendida);
          impressao.append(
            "\nMONTANTE: " +
            NumberFormat
              .getCurrencyInstance(new Locale("pt", "BR"))
              .format(valorPorCategorias) +
            "\n"
          );
        }
      );

    return impressao.toString();
  }
}
