package br.com.alura.services.relatorios;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.categorias.ProdutosMaisCaros;
import br.com.alura.services.relatorios.categorias.TotalDeCategorias;
import br.com.alura.services.relatorios.categorias.VendasPorCategorias;
import java.util.Set;

public class RelatoriosDeCategoria extends Relatorio {

  public RelatoriosDeCategoria(Fechamento fechamento) {
    super(fechamento);
  }

  @Override
  public void imprimirRelatorios() {
    final Set<ItemDeRelatorio> itens = Set.of(
      new VendasPorCategorias(),
      new TotalDeCategorias(),
      new ProdutosMaisCaros()
    );

    itens.forEach(item -> item.imprime(this.fechamento));
  }

  @Override
  public String toString() {
    return "Relatório de categorias aqui";
  }
}
