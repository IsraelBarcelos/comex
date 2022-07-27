package br.com.alura.comex.comercial.dominio.categoria;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepositorioDeCategoria {
    void criarCategoria(Categoria categoria);

    Optional<Categoria> encontrarCategoriaPeloId(Long id);

    Page<Categoria> listarTodasCategorias(Pageable pageable);

    Optional<Categoria> encontrarCategoriaPeloNome(String nome);

    void removerCategoria(Categoria categoria);
}
