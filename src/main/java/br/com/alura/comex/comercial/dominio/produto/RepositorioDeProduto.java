package br.com.alura.comex.comercial.dominio.produto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepositorioDeProduto {
    void criarProduto(Produto produto);

    Optional<Produto> encontrarProdutoPeloId(Long id);

    Page<Produto> listarTodosProdutos(Pageable pageable);

    Optional<Produto> encontrarPeloNome(String string);

    void removerProduto(Produto produto);
}
