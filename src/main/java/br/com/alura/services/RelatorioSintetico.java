package br.com.alura.services;

import br.com.alura.comex.Cliente;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Locale;

public class RelatorioSintetico {

  Fechamento fechamento;

  public RelatorioSintetico(Fechamento fechamento) {
    this.fechamento = fechamento;
    this.imprimeTotalDePedidos();
    this.imprimeTotalDeProdutosVendidos();
    this.imprimeTotalDeCategorias();
    this.imprimeTotalDeVendas();
    this.imprimePedidoMaisBarato();
    this.imprimeProdutoMaisCaro();
    this.imprimeClientesFieis();
  }

  private void imprimeTotalDePedidos() {
    System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
    System.out.printf(
      "- TOTAL DE PEDIDOS REALIZADOS: %s\n",
      this.fechamento.getTotalDePedidosRealizados()
    );
  }

  private void imprimeTotalDeProdutosVendidos() {
    System.out.printf(
      "- TOTAL DE PRODUTOS VENDIDOS: %s\n",
      fechamento.getTotalDeProdutosVendidos()
    );
  }

  private void imprimeTotalDeCategorias() {
    System.out.printf(
      "- TOTAL DE CATEGORIAS: %s\n",
      fechamento.getTotalDeCategorias()
    );
  }

  private void imprimeTotalDeVendas() {
    System.out.printf(
      "- MONTANTE DE VENDAS: %s\n",
      NumberFormat
        .getCurrencyInstance(new Locale("pt", "BR"))
        .format(
          fechamento.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)
        )
    );
  }

  private void imprimePedidoMaisBarato() {
    System.out.printf(
      "- PEDIDO MAIS BARATO: %s (%s)\n",
      NumberFormat
        .getCurrencyInstance(new Locale("pt", "BR"))
        .format(
          fechamento
            .getPedidoMaisBarato()
            .getValorTotal()
            .setScale(2, RoundingMode.HALF_DOWN)
        ),
      fechamento.getPedidoMaisBarato().getProduto()
    );
  }

  private void imprimeProdutoMaisCaro() {
    System.out.printf(
      "- PEDIDO MAIS CARO: %s (%s)\n",
      NumberFormat
        .getCurrencyInstance(new Locale("pt", "BR"))
        .format(
          fechamento
            .getPedidoMaisCaro()
            .getValorTotal()
            .setScale(2, RoundingMode.HALF_DOWN)
        ),
      fechamento.getPedidoMaisCaro().getProduto()
    );
  }

  private void imprimeClientesFieis() {
    System.out.println("\n#### RELATÓRIO DE CLIENTES FIEIS\n");
    fechamento
      .getClientesFieis()
      .stream()
      .sorted(Comparator.comparing(Cliente::getNumeroDePedidos).reversed())
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
