package br.com.alura.comex.utils;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.aplicacao.produto.ProdutoBuilder;
import br.com.alura.comex.comercial.dominio.categoria.RepositorioDeCategoria;
import br.com.alura.comex.comercial.dominio.produto.Produto;
import br.com.alura.comex.comercial.dominio.produto.RepositorioDeProduto;

public class CreateProdutoUtil {
    public final static String nome = "Produto Teste";
    public final static String descricao = "Produto Teste";
    public final static BigDecimal valor = new BigDecimal(10);
    public final static int quantidadeEstoque = 55;

    public static void createProduto(RepositorioDeProduto produtoRepository,
            RepositorioDeCategoria categoriaRepository)
            throws Exception {
        if (!produtoRepository.encontrarPeloNome(nome).isPresent()) {
            CreateCategoriaUtil.createCategoria(categoriaRepository);

            Produto produto = new ProdutoBuilder()
                    .comCategoria(categoriaRepository.encontrarCategoriaPeloNome(CreateCategoriaUtil.nome).get())
                    .comDescricao(descricao)
                    .comNome(nome)
                    .comPrecoUnitario(valor)
                    .comQuantidadeEstoque(quantidadeEstoque)
                    .build();

            produtoRepository.criarProduto(produto);
        }
    }
}
