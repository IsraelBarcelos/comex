package br.com.alura.services.relatorios.pedidos;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;

public class TotalDeProdutosVendidos implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.printf(
      "- TOTAL DE PRODUTOS VENDIDOS: %s\n",
      fechamento.getTotalDeProdutosVendidos()
    );
  }
}
