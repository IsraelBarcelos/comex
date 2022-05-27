package br.com.alura.services.relatorios.pedidos;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;

public class ProdutoMaisVendidoComLimit implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.println("\n#### RELATÓRIO DE PRODUTOS MAIS VENDIDOS");
    fechamento
      .getProdutosMaisVendidoComSkips(3)
      .stream()
      .forEach(
        pedido -> {
          System.out.println(
            "\nPRODUTO: " +
            pedido.getProduto().getNome() +
            "\nQUANTIDADE: " +
            pedido.getQuantidade()
          );
        }
      );
  }
}
