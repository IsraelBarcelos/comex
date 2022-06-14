package br.com.alura.comex.controllers.form;

import br.com.alura.comex.builders.CategoriaBuilder;
import br.com.alura.comex.models.Categoria;

public class CategoriaForm {

  private String nome;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Categoria converter() {
    Categoria categoria = new CategoriaBuilder().comNome(nome).ativo().build();

    return categoria;
  }
}
