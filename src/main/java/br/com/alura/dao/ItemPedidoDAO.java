package br.com.alura.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.models.Categoria;
import br.com.alura.models.ItemPedido;
import br.com.alura.models.Pedido;
import br.com.alura.vo.RelatorioDeClientesMaisLucrativosVO;
import br.com.alura.vo.RelatorioDeProdutosMaisVendidosVO;

public class ItemPedidoDAO {

    private EntityManager em;

    public ItemPedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastra(ItemPedido itemPedido) {
        em.persist(itemPedido);
    }

    public ItemPedido buscaPorId(Long id) {
        return em.find(ItemPedido.class, id);
    }

    public void remove(ItemPedido itemPedido) {
        em.remove(itemPedido);
    }

    public void atualiza(ItemPedido itemPedido) {
        em.merge(itemPedido);
    }

    public List<ItemPedido> listaTodos() {
        return this.em.createQuery("select i from ItemPedido i", ItemPedido.class).getResultList();
    }

    public List<ItemPedido> listaPorPedido(Pedido pedido) {
        return this.em.createQuery(
                "select i from ItemPedido i where i.pedido = :pedido",
                ItemPedido.class)
                .setParameter("pedido", pedido)
                .getResultList();
    }

    public List<ItemPedido> listaPorCategoria(Categoria categoria) {
        return this.em.createQuery(
                "select i from ItemPedido i where i.produto.categoria = :categoria",
                ItemPedido.class)
                .setParameter("categoria", categoria)
                .getResultList();
    }

    public List<RelatorioDeProdutosMaisVendidosVO> produtosMaisVendido() {

        return this.em
                .createQuery(
                        "SELECT new br.com.alura.vo.RelatorioDeProdutosMaisVendidosVO( "
                                + "i.produto.nome, "
                                + "count(i.produto.id)) "
                                + "from ItemPedido i "
                                + "group by i.produto.id "
                                + "order by count(i.produto.id) DESC",
                        RelatorioDeProdutosMaisVendidosVO.class)
                .setMaxResults(3)
                .getResultList();
    }

    public List<RelatorioDeClientesMaisLucrativosVO> listaClientesMaisLucrativos() {
        return this.em
                .createQuery(
                        "SELECT new br.com.alura.vo.RelatorioDeClientesMaisLucrativosVO(i.pedido.cliente, sum(i.precoUnitario * i.quantidade) as lucro) "
                                + "from ItemPedido i "
                                + "group by i.pedido.cliente "
                                + "order by lucro DESC",
                        RelatorioDeClientesMaisLucrativosVO.class)
                .getResultList();
    }
}
