package br.com.alura.comex.comercial.aplicacao.cliente;

import org.springframework.data.domain.Page;

import br.com.alura.comex.comercial.dominio.cliente.Cliente;

public class ClienteDto {
    private String nome;
    private String cpf;
    private String telefone;
    private String local;

    public ClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf().getNumeroCpf();
        this.telefone = cliente.getTelefone().getNumeroComDDD();
        this.local = cliente.getEndereco().getCidade() + "/" + cliente.getEndereco().getEstado();
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

    public static Page<ClienteDto> converter(Page<Cliente> clientes) {
        return clientes.map(ClienteDto::new);
    }

}
