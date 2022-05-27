package br.com.alura.services.relatorios;

import br.com.alura.services.Fechamento;
import java.util.Set;

public abstract class Relatorio {

  Fechamento fechamento;

  public Relatorio(Fechamento fechamento) {
    this.fechamento = fechamento;
  }

  public static void imprimeTodosOsRelatorio(Set<Relatorio> relatorios) {
    relatorios.iterator().forEachRemaining(Relatorio::toString);
  }

  public abstract void imprimirRelatorios();
}
