package br.com.alura.comex;

import br.com.alura.services.ProcessadorDeCsv;
import br.com.alura.services.RelatorioFinal;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    ProcessadorDeCsv listas = new ProcessadorDeCsv("pedidos.csv");
    try {
      listas.execute();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    ArrayList<Pedido> pedidos = listas.getPedidos();

    int totalDeProdutosVendidos = 0;
    int totalDePedidosRealizados = 0;
    BigDecimal montanteDeVendas = BigDecimal.ZERO;
    Pedido pedidoMaisBarato = null;
    Pedido pedidoMaisCaro = null;

    CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas();
    int totalDeCategorias = 0;

    for (int i = 0; i < pedidos.size(); i++) {
      Pedido pedidoAtual = pedidos.get(i);

      if (pedidoAtual == null) {
        break;
      }

      if (
        pedidoMaisBarato == null ||
        pedidoAtual.isMaisBaratoQue(pedidoMaisBarato)
      ) {
        pedidoMaisBarato = pedidoAtual;
      }

      if (pedidoMaisCaro == null || pedidoAtual.isMaisCaroQue(pedidoMaisCaro)) {
        pedidoMaisCaro = pedidoAtual;
      }

      montanteDeVendas =
        montanteDeVendas.add(
          pedidoAtual
            .getPreco()
            .multiply(new BigDecimal(pedidoAtual.getQuantidade()))
        );
      totalDeProdutosVendidos += pedidoAtual.getQuantidade();
      totalDePedidosRealizados++;

      if (!categoriasProcessadas.contains(pedidoAtual.getCategoria())) {
        totalDeCategorias++;
        categoriasProcessadas.add(pedidoAtual.getCategoria());
      }
    }

    new RelatorioFinal(
      totalDePedidosRealizados,
      totalDeProdutosVendidos,
      totalDeCategorias,
      montanteDeVendas,
      pedidoMaisBarato,
      pedidoMaisCaro,
      categoriasProcessadas
    );
  }
}
