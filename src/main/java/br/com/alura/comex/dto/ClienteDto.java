package br.com.alura.comex.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.models.Cliente;

public class ClienteDto {
    private String nome;
    private String cpf;
    private String telefone;
    private EnderecoDto endereco;
    private List<PedidoDto> pedidos;

    public ClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.endereco = new EnderecoDto(cliente.getEndereco());
        this.pedidos = new ArrayList<>();
        this.pedidos.addAll(
                cliente
                        .getPedidos()
                        .stream()
                        .map(PedidoDto::new)
                        .collect(Collectors.toList()));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDto endereco) {
        this.endereco = endereco;
    }

    public List<PedidoDto> getPedidos() {
        return pedidos;
    }
}
