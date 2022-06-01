package br.com.alura.services.relatorios.pedidos;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class PedidoMaisBarato implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.println(geraSaida(fechamento));
  }

  @Override
  public String geraSaida(Fechamento fechamento) {
    StringBuilder saida = new StringBuilder();
    saida.append("- PEDIDO MAIS BARATO: %s (%s)\n");
    saida.append(
      NumberFormat
        .getCurrencyInstance(new Locale("pt", "BR"))
        .format(
          fechamento
            .getPedidoMaisBarato()
            .getValorTotal()
            .setScale(2, RoundingMode.HALF_DOWN)
        )
    );
    saida.append(fechamento.getPedidoMaisBarato().getProduto().getNome());

    return saida.toString();
  }
}
