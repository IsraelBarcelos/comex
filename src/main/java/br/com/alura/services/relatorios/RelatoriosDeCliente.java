package br.com.alura.services.relatorios;

import br.com.alura.comex.Cliente;
import br.com.alura.services.Fechamento;
import java.util.Comparator;

public class RelatoriosDeCliente extends Relatorio {

  public RelatoriosDeCliente(Fechamento fechamento) {
    super(fechamento);
  }

  public void imprimeClientesFieis() {
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

  public void imprimeClientesMaisLucrativos() {
    System.out.println("\n#### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS");
    this.fechamento.getClientesMaisLucrativos(2)
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

  public String toString() {
    return "Relatorio de clientes aqui.";
  }

  @Override
  public void imprimirRelatorios() {
    imprimeClientesFieis();
    imprimeClientesMaisLucrativos();
  }
}
