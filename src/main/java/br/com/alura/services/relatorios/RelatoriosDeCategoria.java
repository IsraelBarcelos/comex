package br.com.alura.services.relatorios;

import java.math.BigDecimal;
import java.util.Comparator;

import br.com.alura.comex.Pedido;
import br.com.alura.services.Fechamento;

public class RelatoriosDeCategoria extends Relatorio {

    public RelatoriosDeCategoria(Fechamento fechamento){
        super(fechamento);
    }

    public void imprimeTotalDeCategorias() {
        System.out.printf(
          "- TOTAL DE CATEGORIAS: %s\n",
          fechamento.getTotalDeCategorias()
        );
      }


      public void imprimeVendasPorCategorias() { //Posso passar esses métodos para o Fechamento, para manter a responsabilidade da classe apenas de gerar o relatório (S)olid
        fechamento
          .getSortedCategoriasProcessadas()
          .stream()
          .forEach(
            categoria -> {
              long quantidadeVendida = fechamento
                .getPedidosDeUmFechamento()
                .getPedidos()
                .stream()
                .filter(
                  pedido -> pedido.getProduto().getCategoria().equals(categoria)
                )
                .map(Pedido::getQuantidade)
                .reduce(Integer::sum)
                .get();
    
              BigDecimal valorPorCategorias = fechamento.getPedidosDeUmFechamento()
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

      public void imprimeProdutosMaisCarosDeCadaCategoria() { //Posso passar esses métodos para o Fechamento, para manter a responsabilidade da classe apenas de gerar o relatório (S)olid
        System.out.println(
          "\n#### RELATÓRIO DE PRODUTOS MAIS CAROS DE CADA CATEGORIA"
        );
    
        this.fechamento.getSortedCategoriasProcessadas()
          .stream()
          .forEach(
            categoria -> {
              System.out.println("\nCATEGORIA: " + categoria);
              Pedido pedidoPlaceholder =
                this.fechamento.getPedidosDeUmFechamento().getPedidos()
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

    @Override
    public String toString() {
        return "Relatório de categorias aqui";
    }
    
}
