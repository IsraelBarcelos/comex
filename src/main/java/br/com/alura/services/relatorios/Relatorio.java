package br.com.alura.services.relatorios;

import java.util.Set;

import br.com.alura.services.Fechamento;

public abstract class Relatorio {
    Fechamento fechamento;
    
    public Relatorio(Fechamento fechamento) {
        this.fechamento = fechamento;
    }

    public static void imprimeTodosOsRelatorio(Set<Relatorio> relatorios) {
        relatorios.iterator().forEachRemaining(Relatorio::toString);
    }
}
