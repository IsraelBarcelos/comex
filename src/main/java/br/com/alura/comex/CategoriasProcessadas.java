package br.com.alura.comex;

import java.util.HashSet;

public class CategoriasProcessadas  {
    HashSet<String> categorias = new HashSet<>();

    public void add(String categoria) {
        categorias.add(categoria);
    }

    public boolean contains(String categoria) {
        return categorias.contains(categoria);
    }

    public HashSet<String> getCategorias() {
        return categorias;
    }
}
