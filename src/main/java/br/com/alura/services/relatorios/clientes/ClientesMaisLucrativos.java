package br.com.alura.services.relatorios.clientes;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.ItemDeRelatorio;

public class ClientesMaisLucrativos implements ItemDeRelatorio {

  @Override
  public void imprime(Fechamento fechamento) {
    System.out.println("\n#### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS");
    fechamento
      .getClientesMaisLucrativos(2)
      .stream()
      .forEach(
        cliente -> {
          System.out.println(
            "\nNOME: " +
            cliente.getNome() +
            "\nNÚMERO DE PEDIDOS: " +
            cliente.getNumeroDePedidos() +
            "\nMONTANTE GASTO: R$ " +
            cliente.getTotalDeCompras()
          );
        }
      );
  }
}
