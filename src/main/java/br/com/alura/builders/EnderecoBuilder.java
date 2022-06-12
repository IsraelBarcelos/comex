package br.com.alura.builders;

import br.com.alura.models.Endereco;

public class EnderecoBuilder {

    private Endereco endereco;

    public EnderecoBuilder() {
        endereco = new Endereco();
    }

    public EnderecoBuilder comRua(String rua) {
        endereco.setRua(rua);
        return this;
    }

    public EnderecoBuilder comNumero(int numero) {
        endereco.setNumero(numero);
        return this;
    }

    public EnderecoBuilder comComplemento(String complemento) {
        endereco.setComplemento(complemento);
        return this;
    }

    public EnderecoBuilder comBairro(String bairro) {
        endereco.setBairro(bairro);
        return this;
    }

    public EnderecoBuilder comCidade(String cidade) {
        endereco.setCidade(cidade);
        return this;
    }

    public EnderecoBuilder comEstado(String estado) {
        endereco.setEstado(estado);
        return this;
    }

    public Endereco build() {
        return endereco;
    }
}
