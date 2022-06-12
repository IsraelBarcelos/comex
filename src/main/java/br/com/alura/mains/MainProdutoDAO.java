package br.com.alura.mains;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.builders.CategoriaBuilder;
import br.com.alura.builders.ProdutoBuilder;
import br.com.alura.dao.CategoriaDAO;
import br.com.alura.dao.ProdutoDAO;
import br.com.alura.models.Categoria;
import br.com.alura.models.Produto;
import br.com.alura.utils.JPAUtil;

public class MainProdutoDAO {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        Categoria categoria1 = new CategoriaBuilder().comNome("carro").ativo().build();
        Categoria categoria2 = new CategoriaBuilder().comNome("moto").ativo().build();
        Categoria categoria3 = new CategoriaBuilder().comNome("aviao").ativo().build();

        categoriaDAO.cadastra(categoria1);
        categoriaDAO.cadastra(categoria2);
        categoriaDAO.cadastra(categoria3);

        Categoria categoriaDeIdUm = categoriaDAO.buscaPorId(15L);

        em.flush();

        Produto produto1 = new ProdutoBuilder()
                .comCategoria(categoria1)
                .comDescricao("É um carro")
                .comNome("Carro sujo")
                .comPrecoUnitario(new BigDecimal(3684.00))
                .comQuantidadeEstoque(25)
                .build();
        Produto produto2 = new ProdutoBuilder()
                .comCategoria(categoria2)
                .comNome("Moto usada")
                .comPrecoUnitario(new BigDecimal(55484.00))
                .comQuantidadeEstoque(0)
                .build();
        Produto produto3 = new ProdutoBuilder()
                .comCategoria(categoria3)
                .comDescricao("É um aviao")
                .comNome("Maquina de matar pombo")
                .comPrecoUnitario(new BigDecimal(654321.00))
                .comQuantidadeEstoque(2)
                .build();
        Produto produto4 = new ProdutoBuilder()
                .comCategoria(categoria1)
                .comDescricao("A moto mais rapida do mundo")
                .comNome("Nome da moto mais cara do mundo")
                .comPrecoUnitario(new BigDecimal(5554448844.00))
                .comQuantidadeEstoque(0)
                .build();

        produtoDAO.cadastra(produto1);
        produtoDAO.cadastra(produto2);
        produtoDAO.cadastra(produto3);
        produtoDAO.cadastra(produto4);

        Produto produto10 = new ProdutoBuilder()
                .comCategoria(categoriaDeIdUm)
                .comDescricao("É um carro")
                .comNome("Carro sujo")
                .comPrecoUnitario(new BigDecimal(3684.00))
                .comQuantidadeEstoque(25)
                .build();

        produtoDAO.cadastra(produto10);

        em.getTransaction().commit();

        produtoDAO.listaIndisponiveis().forEach(System.out::println);

        Categoria categoria = new CategoriaBuilder().comNome("Categoria1")
                .ativo()
                .build();
        em.persist(categoria);
        em.flush();

        Produto produto5 = new ProdutoBuilder()
                .comCategoria(categoria)
                .comNome("Produto 1")
                .comPrecoUnitario(BigDecimal.valueOf(10.00))
                .comQuantidadeEstoque(10)
                .build();
        Produto produto6 = new ProdutoBuilder()
                .comCategoria(categoria)
                .comNome("Produto 2")
                .comPrecoUnitario(BigDecimal.valueOf(20.00))
                .comQuantidadeEstoque(10)
                .build();
        Produto produto7 = new ProdutoBuilder()
                .comCategoria(categoria)
                .comNome("Produto 3")
                .comPrecoUnitario(BigDecimal.valueOf(30.00))
                .comDescricao("descricao")
                .comQuantidadeEstoque(5)
                .build();

        em.persist(produto5);
        em.persist(produto6);
        em.persist(produto7);

        produtoDAO.pedidosNuncaVendidos().stream().forEach(produto -> {
            System.out.println(produto.getId() + " - " + produto.getNome());
        });

        em.close();
    }
}
