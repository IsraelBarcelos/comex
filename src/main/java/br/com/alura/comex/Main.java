package br.com.alura.comex;

import br.com.alura.services.ProcessadorDeCsv;
import br.com.alura.services.RelatorioSintetico;
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

    pedidoMaisBarato =
      pedidos.stream().min(Comparator.comparing(Pedido::getPreco)).get();
    pedidoMaisCaro =
      pedidos.stream().max(Comparator.comparing(Pedido::getPreco)).get();

    montanteDeVendas =
      pedidos.stream().map(Pedido::getValorTotal).reduce(BigDecimal::add).get();

    totalDeProdutosVendidos =
      pedidos.stream().map(Pedido::getQuantidade).reduce(Integer::sum).get();

    totalDePedidosRealizados = pedidos.size();

    pedidos
      .stream()
      .forEach(
        pedido -> {
          if (!categoriasProcessadas.contains(pedido.getCategoria())) {
            categoriasProcessadas.add(pedido.getCategoria());
          }
        }
      );

    totalDeCategorias = categoriasProcessadas.getTotalDeCategorias();

    new RelatorioSintetico(
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
