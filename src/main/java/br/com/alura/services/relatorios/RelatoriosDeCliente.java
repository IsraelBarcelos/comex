package br.com.alura.services.relatorios;

import br.com.alura.services.Fechamento;
import br.com.alura.services.relatorios.clientes.ClientesFieis;
import br.com.alura.services.relatorios.clientes.ClientesMaisLucrativos;
import java.util.Set;

public class RelatoriosDeCliente extends Relatorio {

  public RelatoriosDeCliente(Fechamento fechamento) {
    super(fechamento);
  }

  @Override
  public void imprimirRelatorios() {
    final Set<ItemDeRelatorio> itens = Set.of(
      new ClientesFieis(),
      new ClientesMaisLucrativos()
    );

    itens.forEach(item -> item.imprime(this.fechamento));
  }

  public String toString() {
    return "Relatorio de clientes aqui.";
  }
}
