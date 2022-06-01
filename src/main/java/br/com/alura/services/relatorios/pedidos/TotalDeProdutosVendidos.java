package br.com.alura.services.relatorios.pedidos;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;

public class TotalDeProdutosVendidos implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.println(geraSaida(fechamento));
  }

  @Override
  public String geraSaida(Fechamento fechamento) {
    StringBuilder saida = new StringBuilder();
    saida.append("- TOTAL DE PRODUTOS VENDIDOS: %s\n");
    saida.append(fechamento.getTotalDeProdutosVendidos());

    return saida.toString();
  }
}
