package br.com.alura.services.relatorios.categorias;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;

public class TotalDeCategorias implements ItemDeRelatorio {

  public void imprime(Fechamento fechamento) {
    System.out.printf("- TOTAL DE CATEGORIAS: %s\n", geraSaida(fechamento));
    System.out.println(geraSaida(fechamento));
  }

  @Override
  public String geraSaida(Fechamento fechamento) {
    StringBuilder saida = new StringBuilder();
    saida.append(fechamento.getTotalDeCategorias());
    return saida.toString();
  }
}
