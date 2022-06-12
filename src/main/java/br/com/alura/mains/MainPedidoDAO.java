package br.com.alura.mains;

import javax.persistence.EntityManager;

import br.com.alura.dao.ItemPedidoDAO;
import br.com.alura.utils.JPAUtil;

public class MainPedidoDAO {
    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        // new CategoriaDAO(em).relatorioMontantePorCategoria();(
        ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO(em);

        itemPedidoDAO.produtosMaisVendido().stream().forEach(relatorioDeVendasVO -> {
            System.out.println(relatorioDeVendasVO.getNomeDoProduto() + " vendeu "
                    + relatorioDeVendasVO.getQuantidadeDoProduto() + " vezes");

        });
    }
}
