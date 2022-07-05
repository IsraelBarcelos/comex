package br.com.alura.comex.utils;

import java.math.BigDecimal;

import br.com.alura.comex.builders.ProdutoBuilder;
import br.com.alura.comex.models.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;

public class CreateProdutoUtil {
    public final static String nome = "Produto Teste";
    public final static String descricao = "Produto Teste";
    public final static BigDecimal valor = new BigDecimal(10);
    public final static int quantidadeEstoque = 55;

    public static void createProduto(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository)
            throws Exception {
        if (!produtoRepository.findByNome(nome).isPresent()) {
            CreateCategoriaUtil.createCategoria(categoriaRepository);

            Produto produto = new ProdutoBuilder()
                    .comCategoria(categoriaRepository.findByNome(CreateCategoriaUtil.nome).get())
                    .comDescricao(descricao)
                    .comNome(nome)
                    .comPrecoUnitario(valor)
                    .comQuantidadeEstoque(quantidadeEstoque)
                    .build();

            produtoRepository.save(produto);
        }
    }
}
