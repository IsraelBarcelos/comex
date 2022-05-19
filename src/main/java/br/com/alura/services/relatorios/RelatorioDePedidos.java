package br.com.alura.services.relatorios;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.alura.services.Fechamento;

public class RelatorioDePedidos extends Relatorio {
    
    public RelatorioDePedidos(Fechamento fechamento) {
        super(fechamento);
    }
    
    public void imprimeTotalDePedidos() {
        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf(
          "- TOTAL DE PEDIDOS REALIZADOS: %s\n",
          this.fechamento.getTotalDePedidosRealizados()
        );
      }
    
      public void imprimeTotalDeProdutosVendidos() {
        System.out.printf(
          "- TOTAL DE PRODUTOS VENDIDOS: %s\n",
          fechamento.getTotalDeProdutosVendidos()
        );
      }

      public void imprimeTotalDeVendas() {
        System.out.printf(
          "- MONTANTE DE VENDAS: %s\n",
          NumberFormat
            .getCurrencyInstance(new Locale("pt", "BR"))
            .format(
              fechamento.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)
            )
        );
      }

      public void imprimePedidoMaisBarato() {
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

      public void imprimeProdutoMaisCaro() {
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

      public void imprimeProdutosMaisVendidoComLimit() {
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

    public String toString(){
        return "Relatorio de pedidos aqui.";
    }
}
