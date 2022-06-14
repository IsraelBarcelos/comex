package br.com.alura.comex.dto;

import br.com.alura.comex.models.Categoria;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {

  private String nome;

  public CategoriaDto(Categoria categoria) {
    this.nome = categoria.getNome();
  }

  public String getNome() {
    return nome;
  }

  public static List<CategoriaDto> converter(List<Categoria> categorias) {
    return categorias
      .stream()
      .map(CategoriaDto::new)
      .collect(Collectors.toList());
  }
}
