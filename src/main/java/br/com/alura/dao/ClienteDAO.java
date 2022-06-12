package br.com.alura.dao;

import br.com.alura.models.Cliente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ClienteDAO {

    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastra(Cliente cliente) {
        em.persist(cliente);
    }

    public Cliente buscaPorId(Long id) {
        return em.find(Cliente.class, id);
    }

    public void remove(Cliente cliente) {
        em.remove(cliente);
    }

    public void atualiza(Cliente cliente) {
        em.merge(cliente);
    }

    public List<Cliente> listaTodos() {
        return this.em.createQuery("select c from Cliente c", Cliente.class)
                .getResultList();
    }

    public List<Cliente> listaPorNome(String nome) {
        return this.em.createQuery(
                "select c from Cliente c where c.nome = :nome",
                Cliente.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    private List<Cliente> listaClientesQueTiveramPeloMenosUmPedido() {
        return this.em.createQuery(
                "select c from Cliente c where c.pedidos is not empty",
                Cliente.class)
                .getResultList();
    }

    public void relatorioCliente() {
        List<Cliente> clientes = this.listaClientesQueTiveramPeloMenosUmPedido();
        for (Cliente cliente : clientes) {
            System.out.print("\n" + cliente.getNome());
            System.out
                    .println(" realizou " + new PedidoDAO(em).quantidadeDePedidosDeUmCliente(cliente) + " pedido(s)");
        }
    }

    public Cliente listaUnicoClientePorNome(String nome) {
        try {
            Cliente cliente = this.em.createQuery("select c from Cliente c where c.nome = :nome",
                    Cliente.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
            return cliente;
        } catch (NoResultException e) {
            return new Cliente();
        }
    }

    public List<Cliente> encontrarClientesPorDadosComCriteriaAPI(String nome, String cpf, String telefone) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        Predicate filtros = builder.and();

        if (nome != null && !nome.trim().equals("")) {
            filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
        }
        if (cpf != null && !cpf.trim().equals("")) {
            filtros = builder.and(filtros, builder.equal(from.get("cpf"), cpf));
        }
        if (telefone != null && !telefone.trim().equals("")) {
            filtros = builder.and(filtros, builder.equal(from.get("telefone"), telefone));
        }

        query.where(filtros);

        return em.createQuery(query).getResultList();
    }
}