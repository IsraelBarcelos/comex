package br.com.alura.comex.aplicacao.categoria;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.dominio.categoria.Categoria;
import br.com.alura.comex.dominio.categoria.CategoriaBuilder;

public class CategoriaForm {
  @NotNull
  @NotEmpty
  @Length(min = 2)
  private String nome;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Categoria converter() {
    Categoria categoria = new CategoriaBuilder().comNome(nome).ativaCategoria().build();

    return categoria;
  }
}
