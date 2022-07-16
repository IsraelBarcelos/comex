package br.com.alura.comex.comercial.aplicacao.categoria;

import org.springframework.data.domain.Page;

import br.com.alura.comex.comercial.dominio.categoria.Categoria;

public class CategoriaDto {

    private String nome;
    private Long id;

    public CategoriaDto(Categoria categoria) {
        this.nome = categoria.getNome();
        this.id = categoria.getId();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Page<CategoriaDto> converter(Page<Categoria> categorias) {
        return categorias.map(CategoriaDto::new);
    }
}
