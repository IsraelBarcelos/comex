package br.com.alura.comex.utils;

import br.com.alura.comex.comercial.aplicacao.categoria.CategoriaBuilder;
import br.com.alura.comex.comercial.infra.categoria.CategoriaRepositoryComJPA;

public class CreateCategoriaUtil {

    public final static String nome = "teste";

    public static void createCategoria(CategoriaRepositoryComJPA categoriaRepository) throws Exception {
        if (!categoriaRepository.encontrarCategoriaPeloNome(nome).isPresent()) {
            categoriaRepository.save(new CategoriaBuilder().comNome(nome).build());
        }
    }
}
