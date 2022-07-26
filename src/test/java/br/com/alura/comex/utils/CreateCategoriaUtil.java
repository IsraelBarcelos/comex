package br.com.alura.comex.utils;

import br.com.alura.comex.comercial.aplicacao.categoria.CategoriaBuilder;
import br.com.alura.comex.comercial.infra.categoria.CategoriaRepository;

public class CreateCategoriaUtil {

    public final static String nome = "teste";

    public static void createCategoria(CategoriaRepository categoriaRepository) throws Exception {
        if (!categoriaRepository.findByNome(nome).isPresent()) {
            categoriaRepository.save(new CategoriaBuilder().comNome(nome).build());
        }
    }
}
