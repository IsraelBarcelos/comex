package br.com.alura.services.relatorios.pedidos;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class TotalDeVendas implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.printf(
      "- MONTANTE DE VENDAS: %s\n",
      NumberFormat
        .getCurrencyInstance(new Locale("pt", "BR"))
        .format(
          fechamento.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)
        )
    );
  }
}
