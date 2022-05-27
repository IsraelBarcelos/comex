package br.com.alura.services.relatorios;

import br.com.alura.services.Fechamento;

public class RelatorioSintetico extends Relatorio {

  public RelatorioSintetico(Fechamento fechamento) {
    super(fechamento);
  }

  private void imprimePedidoMaisCaro() {
    System.out.println(
      "PEDIDO MAIS CARO: R$ " +
      this.fechamento.getPedidoMaisCaro().getValorTotal() +
      " - " +
      this.fechamento.getPedidoMaisCaro().getProduto().getNome()
    );
  }

  private void imprimePedidoMaisBarato() {
    System.out.println(
      "PEDIDO MAIS BARATO: R$ " +
      this.fechamento.getPedidoMaisBarato().getProduto().getPreco() +
      " - " +
      this.fechamento.getPedidoMaisBarato().getProduto().getNome()
    );
  }

  private void imprimeTotalDeCategorias() {
    System.out.println(
      "TOTAL DE CATEGORIAS: " + this.fechamento.getTotalDeCategorias()
    );
  }

  private void imprimeMontanteDeVendas() {
    System.out.println(
      "MONTANTE DE VENDAS: R$ " + this.fechamento.getMontanteDeVendas()
    );
  }

  private void imprimeTotalDeProdutosVendidos() {
    System.out.println(
      "TOTAL DE PRODUTOS VENDIDOS: " +
      this.fechamento.getTotalDeProdutosVendidos()
    );
  }

  private void imprimeTotalDethisPedidosRealizados() {
    System.out.println(
      "TOTAL DE PEDIDOS REALIZADOS: " +
      super.fechamento.getTotalDePedidosRealizados()
    );
  }

  @Override
  public String toString() {
    return "relatorio";
  }

  @Override
  public void imprimirRelatorios() {
    imprimeTotalDethisPedidosRealizados();
    imprimeTotalDeProdutosVendidos();
    imprimeTotalDeCategorias();
    imprimeMontanteDeVendas();
    imprimePedidoMaisBarato();
    imprimePedidoMaisCaro();
  }
}
