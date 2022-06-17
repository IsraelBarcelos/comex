package br.com.alura.comex.controllers.form;

import br.com.alura.comex.builders.CategoriaBuilder;
import br.com.alura.comex.models.Categoria;

public class AtivaDesativaCategoriaDto {

    private boolean ativo;
    private String nome;

    public AtivaDesativaCategoriaDto(Categoria categoria) {
        this.ativo = categoria.isAtivo();
        this.nome = categoria.getNome();
    }

    public boolean getAtivo() {
        return ativo;
    }

    public String getNome() {
        return nome;
    }

    public Categoria converter() {
        return new CategoriaBuilder()
                .comNome(nome)
                .ativo(ativo)
                .build();
    }
}
