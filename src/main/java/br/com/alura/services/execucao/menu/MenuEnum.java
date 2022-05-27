package br.com.alura.services.execucao.menu;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.Relatorio;
import br.com.alura.services.relatorios.RelatorioDePedidos;
import br.com.alura.services.relatorios.RelatorioSintetico;
import br.com.alura.services.relatorios.RelatoriosDeCategoria;
import br.com.alura.services.relatorios.RelatoriosDeCliente;
import java.util.function.Function;

public enum MenuEnum {
  SINTETICO(d -> new RelatorioSintetico(d)),
  CLIENTES(d -> new RelatoriosDeCliente(d)),
  PEDIDOS(d -> new RelatorioDePedidos(d)),
  CATEGORIAS(d -> new RelatoriosDeCategoria(d));

  private Function<Fechamento, Relatorio> relatorio;

  private MenuEnum(Function<Fechamento, Relatorio> relatorio) {
    this.relatorio = relatorio;
  }

  public Relatorio getRelatorio(Fechamento fechamento) {
    return this.relatorio.apply(fechamento);
  }
}
