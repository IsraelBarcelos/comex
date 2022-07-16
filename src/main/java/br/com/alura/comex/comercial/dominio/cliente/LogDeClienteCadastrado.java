package br.com.alura.comex.comercial.dominio.cliente;

import br.com.alura.comex.comercial.dominio.Evento;
import br.com.alura.comex.comercial.dominio.Ouvinte;

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
