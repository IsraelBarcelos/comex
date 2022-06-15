package br.com.alura.comex.dto;

import br.com.alura.comex.models.Cliente;

public class ClienteDto {
    private String nome;
    private String cpf;
    private String telefone;
    // private EnderecoDto endereco;
    // private List<PedidoDto> pedidos;

    private String local;

    public ClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.local = cliente.getEndereco().getCidade() + "/" + cliente.getEndereco().getEstado();
        // this.endereco = new EnderecoDto(cliente.getEndereco());
        // this.pedidos = new ArrayList<>();
        // this.pedidos.addAll(
        // cliente
        // .getPedidos()
        // .stream()
        // .map(PedidoDto::new)
        // .collect(Collectors.toList()));
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    // public EnderecoDto getEndereco() {
    // return endereco;
    // }

    // public void setEndereco(EnderecoDto endereco) {
    // this.endereco = endereco;
    // }

    // public List<PedidoDto> getPedidos() {
    // return pedidos;
    // }
}
