package br.com.alura.comex.comercial.dominio.cliente;

import br.com.alura.comex.shared.dominio.Cpf;

public class ClienteNaoEncontrado extends RuntimeException {

    public ClienteNaoEncontrado(Cpf cpf) {
        super("Cliente com CPF " + cpf.getNumeroCpf() + " não encontrado");
    }

    public ClienteNaoEncontrado(String nome) {
        super("Cliente com nome " + nome + " não encontrado");
    }

}