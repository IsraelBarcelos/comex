package br.com.alura.comex.comercial.infra.produto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.comex.comercial.dominio.produto.Produto;
import br.com.alura.comex.comercial.dominio.produto.RepositorioDeProduto;

public interface ProdutoRepositoryComJPA extends RepositorioDeProduto, JpaRepository<Produto, Long> {

    default void criarProduto(Produto produto) {
        this.save(produto);
    }

    @Query("select p from Produto p where p.id = ?1")
    Optional<Produto> encontrarProdutoPeloId(Long id);

    @Query("select p from Produto p")
    Page<Produto> listarTodosProdutos(Pageable pageable);

    @Query("select p from Produto p where p.nome = ?1")
    Optional<Produto> encontrarPeloNome(String string);

    @Override
    default void removerProduto(Produto produto) {
        this.delete(produto);
    }
}
