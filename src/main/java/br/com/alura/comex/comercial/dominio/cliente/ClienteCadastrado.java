package br.com.alura.comex.comercial.dominio.cliente;

import java.time.LocalDateTime;
import java.util.Map;

import br.com.alura.comex.shared.dominio.Cpf;
import br.com.alura.comex.shared.dominio.Evento;
import br.com.alura.comex.shared.dominio.TipoDeEvento;

public class ClienteCadastrado implements Evento {

    private final Cpf cpfDoCliente;
    private final LocalDateTime momento;

    public ClienteCadastrado(Cpf cpfDoCliente) {
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

    @Override
    public TipoDeEvento tipo() {
        return TipoDeEvento.CLIENTE_CADASTRADO;
    }

    @Override
    public Map<String, Object> informacoes() {
        return Map.of("cpf", this.cpfDoCliente);
    }
}
