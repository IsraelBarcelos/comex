package br.com.alura.comex.dto;

import br.com.alura.comex.models.Categoria;

import org.springframework.data.domain.Page;

public class CategoriaDto {

    private String nome;

    public CategoriaDto(Categoria categoria) {
        this.nome = categoria.getNome();
    }

    public String getNome() {
        return nome;
    }

    public static Page<CategoriaDto> converter(Page<Categoria> categorias) {
        return categorias.map(CategoriaDto::new);
    }
}
