package br.com.alura.comex.builders;

import br.com.alura.comex.models.Categoria;

public class CategoriaBuilder {

    private Categoria categoria;

    public CategoriaBuilder() {
        categoria = new Categoria();
    }

    public CategoriaBuilder comNome(String nome) {
        categoria.setNome(nome);
        return this;
    }

    public CategoriaBuilder ativo() {
        categoria.setAtivo(true);
        return this;
    }

    public CategoriaBuilder naoAtivo() {
        categoria.setAtivo(false);
        return this;
    }

    public Categoria build() {
        return categoria;
    }
}
