package br.com.alura.services;

import br.com.alura.comex.CategoriasProcessadas;
import br.com.alura.comex.Pedido;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class RelatorioSintetico {

  public RelatorioSintetico(
    int totalDePedidosRealizados,
    int totalDeProdutosVendidos,
    int totalDeCategorias,
    BigDecimal montanteDeVendas,
    Pedido pedidoMaisBarato,
    Pedido pedidoMaisCaro,
    CategoriasProcessadas categoriasProcessadas
  ) {
    System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
    System.out.printf(
      "- TOTAL DE PEDIDOS REALIZADOS: %s\n",
      totalDePedidosRealizados
    );
    System.out.printf(
      "- TOTAL DE PRODUTOS VENDIDOS: %s\n",
      totalDeProdutosVendidos
    );
    System.out.printf("- TOTAL DE CATEGORIAS: %s\n", totalDeCategorias);
    System.out.printf(
      "- MONTANTE DE VENDAS: %s\n",
      NumberFormat
        .getCurrencyInstance(new Locale("pt", "BR"))
        .format(montanteDeVendas.setScale(2, RoundingMode.HALF_DOWN))
    );
    System.out.printf(
      "- PEDIDO MAIS BARATO: %s (%s)\n",
      NumberFormat
        .getCurrencyInstance(new Locale("pt", "BR"))
        .format(
          pedidoMaisBarato.getValorTotal().setScale(2, RoundingMode.HALF_DOWN)
        ),
      pedidoMaisBarato.getProduto()
    );
    System.out.printf(
      "- PEDIDO MAIS CARO: %s (%s)\n",
      NumberFormat
        .getCurrencyInstance(new Locale("pt", "BR"))
        .format(
          pedidoMaisCaro.getValorTotal().setScale(2, RoundingMode.HALF_DOWN)
        ),
      pedidoMaisCaro.getProduto()
    );
  }
}
