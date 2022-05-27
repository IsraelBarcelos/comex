package br.com.alura.services.relatorios.pedidos;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class PedidoMaisCaro implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.printf(
      "- PEDIDO MAIS CARO: %s (%s)\n",
      NumberFormat
        .getCurrencyInstance(new Locale("pt", "BR"))
        .format(
          fechamento
            .getPedidoMaisCaro()
            .getValorTotal()
            .setScale(2, RoundingMode.HALF_DOWN)
        ),
      fechamento.getPedidoMaisCaro().getProduto().getNome()
    );
  }
}
