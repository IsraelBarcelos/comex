package br.com.alura.comex;

import java.util.HashSet;

public class CategoriasProcessadas {

  HashSet<String> categorias = new HashSet<>();

  public HashSet<String> getCategorias() {
    return categorias;
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
