package br.com.alura.comex.comercial.infra.categoria;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.comex.comercial.dominio.categoria.Categoria;
import br.com.alura.comex.comercial.dominio.categoria.RepositorioDeCategoria;

public interface CategoriaRepositoryComJPA extends RepositorioDeCategoria, JpaRepository<Categoria, Long> {

  default void criarCategoria(Categoria categoria) {
    this.save(categoria);
  }

  @Query("select c from Categoria c where c.id = ?1")
  Optional<Categoria> encontrarCategoriaPeloId(Long id);

  @Query("select c from Categoria c")
  Page<Categoria> listarTodasCategorias(Pageable pageable);

  @Query("select c from Categoria c where c.nome = ?1")
  Optional<Categoria> encontrarCategoriaPeloNome(String nome);

  default void removerCategoria(Categoria categoria) {
    this.delete(categoria);
  }
}
