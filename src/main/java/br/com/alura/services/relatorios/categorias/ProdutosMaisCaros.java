package br.com.alura.services.relatorios.categorias;

import br.com.alura.comex.Pedido;
import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Locale;

public class ProdutosMaisCaros implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.println(
      "\n#### RELATÓRIO DE PRODUTOS MAIS CAROS DE CADA CATEGORIA"
    );
    System.out.println(geraSaida(fechamento));
  }

  @Override
  public String geraSaida(Fechamento fechamento) {
    //TODO: Da pra fazer a sida ser um map(string, tuple)

    StringBuilder saida = new StringBuilder();

    fechamento
      .getSortedCategoriasProcessadas()
      .stream()
      .forEach(
        categoria -> {
          saida.append("\nCATEGORIA: " + categoria);
          Pedido pedidoPlaceholder = fechamento
            .getPedidosDeUmFechamento()
            .getPedidos()
            .stream()
            .filter(
              pedido -> pedido.getProduto().getCategoria().equals(categoria)
            )
            .max(Comparator.comparing(pedido -> pedido.getProduto().getPreco()))
            .get();
          saida.append(
            "\nPRODUTO: " + pedidoPlaceholder.getProduto().getNome()
          );
          saida.append(
            "\nPREÇO: " +
            NumberFormat
              .getCurrencyInstance(new Locale("pt", "BR"))
              .format(pedidoPlaceholder.getProduto().getPreco())
          );
        }
      );
    saida.append("\n");
    return saida.toString();
  }
}
