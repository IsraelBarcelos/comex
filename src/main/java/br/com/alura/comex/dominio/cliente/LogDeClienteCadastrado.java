package br.com.alura.comex.dominio.cliente;

import br.com.alura.comex.dominio.Evento;
import br.com.alura.comex.dominio.Ouvinte;

public class LogDeClienteCadastrado extends Ouvinte {

    public void reageAo(Evento clienteCadastrado) {
        System.out.println(
                "Cliente cadastrado: " + ((ClienteCadastrado) clienteCadastrado).getCpfDoCliente() + " - "
                        + clienteCadastrado.momento());
    }

    @Override
    protected boolean deveProcessar(Evento evento) {
        return evento instanceof ClienteCadastrado;
    }
}
