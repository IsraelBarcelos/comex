package br.com.alura.comex.controllers.form;

import br.com.alura.comex.builders.ClienteBuilder;
import br.com.alura.comex.builders.EnderecoBuilder;
import br.com.alura.comex.models.Cliente;
import br.com.alura.comex.models.Endereco;

public class ClienteForm {

    private String nome;
    private String cpf;
    private String telefone;
    private String rua;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente converter() {
        Endereco endereco = new EnderecoBuilder()
                .comRua(rua)
                .comNumero(numero)
                .comComplemento(complemento)
                .comBairro(bairro)
                .comCidade(cidade)
                .comEstado(estado)
                .build();
        Cliente cliente = new ClienteBuilder()
                .comNome(nome)
                .comCpf(cpf)
                .comTelefone(telefone)
                .comEndereco(endereco)
                .build();

        return cliente;
    }
}
