package br.com.alura.comex.utils;

import br.com.alura.comex.comercial.aplicacao.categoria.CategoriaBuilder;
import br.com.alura.comex.comercial.dominio.categoria.RepositorioDeCategoria;

public class CreateCategoriaUtil {

    public final static String nome = "teste";

    public static void createCategoria(RepositorioDeCategoria categoriaRepository) throws Exception {
        if (!categoriaRepository.encontrarCategoriaPeloNome(nome).isPresent()) {
            categoriaRepository.criarCategoria(new CategoriaBuilder().comNome(nome).build());
        }
    }
}
