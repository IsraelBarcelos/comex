package br.com.alura.services.relatorios.clientes;

import br.com.alura.comex.Cliente;
import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;
import java.util.Comparator;

public class ClientesFieis implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.println("\n#### RELATÓRIO DE CLIENTES FIEIS\n");

    System.out.println(geraSaida(fechamento));
  }

  @Override
  public String geraSaida(Fechamento fechamento) {
    StringBuilder saida = new StringBuilder();

    fechamento
      .getClientesFieis()
      .stream()
      .sorted(Comparator.comparing(Cliente::getNome))
      .forEach(
        cliente -> {
          saida.append("NOME: " + cliente.getNome() + "\n");
          saida.append("Nº DE PEDIDOS: " + cliente.getNumeroDePedidos() + "\n");
          saida.append("\n");
        }
      );
    return saida.toString();
  }
}
