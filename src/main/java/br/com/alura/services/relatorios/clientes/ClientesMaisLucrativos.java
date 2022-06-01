package br.com.alura.services.relatorios.clientes;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;
import java.text.NumberFormat;
import java.util.Locale;

public class ClientesMaisLucrativos implements ItemDeRelatorio {

  Locale ptbr = new Locale("pt", "BR");

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.println("\n#### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS");

    System.out.println(geraSaida(fechamento));
  }

  @Override
  public String geraSaida(Fechamento fechamento) {
    StringBuilder saida = new StringBuilder();
    fechamento
      .getClientesMaisLucrativos(2)
      .stream()
      .forEach(
        cliente -> {
          saida.append(
            "NOME: " +
            cliente.getNome() +
            "\nNº DE PEDIDOS: " +
            cliente.getNumeroDePedidos() +
            "\nMONTANTE GASTO: " +
            NumberFormat
              .getCurrencyInstance(ptbr)
              .format(cliente.getTotalDeCompras()) +
            "\n"
          );
        }
      );
    return saida.toString();
  }
}
