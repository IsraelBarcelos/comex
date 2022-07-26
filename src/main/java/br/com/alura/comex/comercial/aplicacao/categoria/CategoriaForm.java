package br.com.alura.comex.comercial.aplicacao.categoria;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.comercial.dominio.categoria.Categoria;

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
    return new CategoriaBuilder().comNome(nome).ativaCategoria().build();
  }
}
