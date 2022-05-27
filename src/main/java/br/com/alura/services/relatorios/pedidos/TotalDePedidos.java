package br.com.alura.services.relatorios.pedidos;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;

public class TotalDePedidos implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
    System.out.printf(
      "- TOTAL DE PEDIDOS REALIZADOS: %s\n",
      fechamento.getTotalDePedidosRealizados()
    );
  }
}
