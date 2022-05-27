package br.com.alura.services.relatorios.categorias;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;

public class TotalDeCategorias implements ItemDeRelatorio {

  public void imprime(Fechamento fechamento) {
    System.out.printf(
      "- TOTAL DE CATEGORIAS: %s\n",
      fechamento.getTotalDeCategorias()
    );
  }
}
