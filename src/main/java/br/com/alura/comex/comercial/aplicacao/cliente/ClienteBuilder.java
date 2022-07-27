package br.com.alura.comex.comercial.aplicacao.cliente;

import br.com.alura.comex.comercial.dominio.cliente.Cliente;
import br.com.alura.comex.comercial.dominio.cliente.Endereco;
import br.com.alura.comex.comercial.dominio.cliente.Telefone;
import br.com.alura.comex.comercial.dominio.usuario.Usuario;
import br.com.alura.comex.shared.dominio.Cpf;

public class ClienteBuilder {

    private Cliente cliente;

    public ClienteBuilder() {
        cliente = new Cliente();
    }

    public ClienteBuilder comNome(String nome) {
        this.cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder comCpf(Cpf cpf) {
        this.cliente.setCpf(cpf);
        return this;
    }

    public ClienteBuilder comTelefone(Telefone telefone) {
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
