package br.com.alura.services.relatorios.clientes;

import br.com.alura.comex.Cliente;
import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;
import java.util.Comparator;

public class ClientesFieis implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.println("\n#### RELATÓRIO DE CLIENTES FIEIS\n");
    fechamento
      .getClientesFieis()
      .stream()
      .sorted(Comparator.comparing(Cliente::getNome))
      .forEach(
        cliente -> {
          System.out.println("NOME: " + cliente.getNome());
          System.out.println(
            "NÚMERO DE PEDIDOS: " + cliente.getNumeroDePedidos() + "\n"
          );
        }
      );
  }
}
