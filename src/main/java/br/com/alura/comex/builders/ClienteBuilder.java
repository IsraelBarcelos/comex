package br.com.alura.comex.builders;

import br.com.alura.comex.models.Cliente;
import br.com.alura.comex.models.Endereco;
import br.com.alura.comex.models.Usuario;

public class ClienteBuilder {

    private Cliente cliente;

    public ClienteBuilder() {
        cliente = new Cliente();
    }

    public ClienteBuilder comNome(String nome) {
        this.cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder comCpf(String cpf) {
        this.cliente.setCpf(cpf);
        return this;
    }

    public ClienteBuilder comTelefone(String telefone) {
        this.cliente.setTelefone(telefone);
        return this;
    }

    public ClienteBuilder comEndereco(Endereco endereco) {
        this.cliente.setEndereco(endereco);
        return this;
    }

    public ClienteBuilder comUsuario(Usuario usuario) {
        this.cliente.setUsuario(usuario);
        return this;
    }

    public Cliente build() {
        return cliente;
    }

}
