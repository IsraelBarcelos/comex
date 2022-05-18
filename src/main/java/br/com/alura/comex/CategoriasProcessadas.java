package br.com.alura.comex;

import java.util.ArrayList;
import java.util.List;

public class CategoriasProcessadas {

  ArrayList<String> categorias;

  public CategoriasProcessadas() {
    this.categorias = new ArrayList<>();
  }

  public List<String> getSortedCategorias() {
    return this.categorias.stream().sorted((a, b) -> a.compareTo(b)).toList();
  }

  public ArrayList<String> getCategorias() {
    return this.categorias;
  }

  public boolean contains(String categoria) {
    return categorias.contains(categoria);
  }

  public void add(String categoria) {
    categorias.add(categoria);
  }

  public int getTotalDeCategorias() {
    return categorias.size();
  }
}
