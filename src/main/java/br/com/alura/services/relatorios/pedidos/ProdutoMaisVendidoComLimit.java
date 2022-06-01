package br.com.alura.services.relatorios.pedidos;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;

public class ProdutoMaisVendidoComLimit implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.println("\n#### RELATÓRIO DE PRODUTOS MAIS VENDIDOS\n");
    System.out.println(geraSaida(fechamento));
  }

  @Override
  public String geraSaida(Fechamento fechamento) {
    StringBuilder saida = new StringBuilder();
    fechamento
      .getProdutosMaisVendidoComSkips(3)
      .stream()
      .forEach(
        pedido -> {
          saida.append("PRODUTO: " + pedido.getProduto().getNome() + "\n");
          saida.append("QUANTIDADE: " + pedido.getQuantidade() + "\n");
          saida.append("\n");
        }
      );

    return saida.toString();
  }
}
