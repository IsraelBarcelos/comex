package br.com.alura.comex.comercial.dominio.cliente;

import java.time.LocalDateTime;

import br.com.alura.comex.comercial.dominio.Evento;

public class ClienteCadastrado implements Evento {

    private final Cpf cpfDoCliente;
    private final LocalDateTime momento;

    public ClienteCadastrado(Cpf cpfDoCliente, LocalDateTime momento) {
        this.cpfDoCliente = cpfDoCliente;
        this.momento = LocalDateTime.now();
    }

    @Override
    public LocalDateTime momento() {
        return this.momento;
    }

    public Cpf getCpfDoCliente() {
        return this.cpfDoCliente;
    }
}
