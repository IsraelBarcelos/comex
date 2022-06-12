package br.com.alura.mains;

import br.com.alura.builders.ClienteBuilder;
import br.com.alura.builders.EnderecoBuilder;
import br.com.alura.dao.ClienteDAO;
import br.com.alura.dao.ItemPedidoDAO;
import br.com.alura.models.Cliente;
import br.com.alura.utils.JPAUtil;

import javax.persistence.EntityManager;

public class MainClienteDAO {

    public static void main(String[] args) {
        Cliente cliente1 = new ClienteBuilder()
                .comNome("Maria")
                .comCpf("123.456.789-10")
                .comTelefone("(11) 98765-4321")
                .comEndereco(
                        new EnderecoBuilder()
                                .comRua("Rua dos Bobos")
                                .comNumero(0)
                                .comComplemento("Apto. 101").comBairro("centro")
                                .comCidade("são paulo")
                                .comEstado("SP")
                                .build())
                .build();
        Cliente cliente2 = new ClienteBuilder()
                .comNome("Joao")
                .comCpf("123.456.789-10")
                .comTelefone("(11) 98765-4321")
                .comEndereco(
                        new EnderecoBuilder()
                                .comRua("Rua dos Bobos")
                                .comNumero(0)
                                .comComplemento("Apto. 101").comBairro("centro")
                                .comCidade("são paulo")
                                .comEstado("SP")
                                .build())
                .build();
        Cliente cliente3 = new ClienteBuilder()
                .comNome("Maria")
                .comCpf("123.456.789-10")
                .comTelefone("(11) 98765-4321")
                .comEndereco(
                        new EnderecoBuilder()
                                .comRua("Rua dos Bobos")
                                .comNumero(0)
                                .comComplemento("Apto. 101").comBairro("centro")
                                .comCidade("são paulo")
                                .comEstado("SP")
                                .build())
                .build();

        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        ClienteDAO clienteDAO = new ClienteDAO(em);

        clienteDAO.cadastra(cliente1);
        clienteDAO.cadastra(cliente2);
        clienteDAO.cadastra(cliente3);

        clienteDAO
                .listaPorNome(cliente3.getNome())
                .stream()
                .forEach(System.out::println);

        em.getTransaction().commit();

        clienteDAO.relatorioCliente();
        new ItemPedidoDAO(em).listaClientesMaisLucrativos().forEach(relatorioDeClientesMaisLucrativosVO -> {
            System.out.println(relatorioDeClientesMaisLucrativosVO.getCliente().getNome() + " vendeu "
                    + relatorioDeClientesMaisLucrativosVO.getValorTotal() + " reais");

        });

    }
}
