package br.com.alura.services;

import br.com.alura.comex.Cliente;
import br.com.alura.comex.Pedido;
import java.math.BigDecimal;
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
    this.imprimeVendasPorCategorias();
    this.ImprimeProdutosMaisVendidoComLimit();
    this.ImprimeProdutosMaisCarosDeCadaCategoria();
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
      fechamento.getPedidoMaisBarato().getProduto().getNome()
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
      fechamento.getPedidoMaisCaro().getProduto().getNome()
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

  private void imprimeVendasPorCategorias() { //Posso passar esses métodos para o Fechamento, para manter a responsabilidade da classe apenas de gerar o relatório (S)olid
    fechamento
      .getSortedCategoriasProcessadas()
      .stream()
      .forEach(
        categoria -> {
          long quantidadeVendida = fechamento.pedidosDeUmFechamento
            .getPedidos()
            .stream()
            .filter(
              pedido -> pedido.getProduto().getCategoria().equals(categoria)
            )
            .map(Pedido::getQuantidade)
            .reduce(Integer::sum)
            .get();

          BigDecimal valorPorCategorias = fechamento.pedidosDeUmFechamento
            .getPedidos()
            .stream()
            .filter(
              pedido -> pedido.getProduto().getCategoria().equals(categoria)
            )
            .sorted(
              Comparator.comparing(pedido -> pedido.getProduto().getPreco())
            )
            .map(Pedido::getValorTotal)
            .reduce(BigDecimal::add)
            .get();

          System.out.println("\nCATEGORIA: " + categoria);
          System.out.println("QUANTIDADE VENDIDA: " + quantidadeVendida);
          System.out.println("MONTANTE: R$" + valorPorCategorias);
        }
      );
  }

  private void ImprimeProdutosMaisVendidoComLimit() {
    System.out.println("\n#### RELATÓRIO DE PRODUTOS MAIS VENDIDOS");
    this.fechamento.getProdutosMaisVendidoComSkips(3)
      .stream()
      .forEach(
        pedido -> {
          System.out.println(
            "\nPRODUTO: " +
            pedido.getProduto().getNome() +
            "\nQUANTIDADE: " +
            pedido.getQuantidade()
          );
        }
      );
  }

  private void ImprimeProdutosMaisCarosDeCadaCategoria() { //Posso passar esses métodos para o Fechamento, para manter a responsabilidade da classe apenas de gerar o relatório (S)olid
    System.out.println(
      "\n#### RELATÓRIO DE PRODUTOS MAIS CAROS DE CADA CATEGORIA"
    );

    this.fechamento.getSortedCategoriasProcessadas()
      .stream()
      .forEach(
        categoria -> {
          System.out.println("\nCATEGORIA: " + categoria);
          Pedido pedidoPlaceholder =
            this.fechamento.pedidosDeUmFechamento.getPedidos()
              .stream()
              .filter(
                pedido -> pedido.getProduto().getCategoria().equals(categoria)
              )
              .max(
                Comparator.comparing(pedido -> pedido.getProduto().getPreco())
              )
              .get();
          System.out.println(
            "PRODUTO: " + pedidoPlaceholder.getProduto().getNome()
          );
          System.out.println(
            "PREÇO: R$ " + pedidoPlaceholder.getProduto().getPreco()
          );
        }
      );
  }
}
