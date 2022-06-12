package br.com.alura.processadores;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.alura.builders.CategoriaBuilder;
import br.com.alura.builders.ClienteBuilder;
import br.com.alura.builders.EnderecoBuilder;
import br.com.alura.builders.ItemPedidoBuilder;
import br.com.alura.builders.PedidoBuilder;
import br.com.alura.builders.ProdutoBuilder;
import br.com.alura.dao.ClienteDAO;
import br.com.alura.models.Categoria;
import br.com.alura.models.Cliente;
import br.com.alura.models.ItemPedido;
import br.com.alura.models.Pedido;
import br.com.alura.models.Produto;
import br.com.alura.models.TipoDescontoPedido;
import br.com.alura.utils.JPAUtil;

public class ProcessadorDeCsv implements ProcessadorInterface {

    private String path;

    public ProcessadorDeCsv(String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        ProcessadorDeCsv processador = new ProcessadorDeCsv("pedidos.csv");
        try {
            processador.execute();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void execute() throws IOException, URISyntaxException {
        URL recursoCSV = ClassLoader.getSystemResource(this.path);

        if (recursoCSV == null) {
            throw new IllegalArgumentException("Arquivo não encontrado");
        }

        Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

        Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);

        leitorDeLinhas.nextLine();

        this.transformarDadosEmLinhas(leitorDeLinhas);

        leitorDeLinhas.close();
    }

    private void transformarDadosEmLinhas(Scanner leitorDeLinhas) {

        while (leitorDeLinhas.hasNextLine()) {
            String linha = leitorDeLinhas.nextLine();
            String[] registro = linha.split(",");

            EntityManager em = JPAUtil.getEntityManager();

            em.getTransaction().begin();

            String categoria = registro[0];
            Categoria categoriaBanco = new CategoriaBuilder()
                    .comNome(categoria)
                    .ativo()
                    .build();

            em.flush();

            String produto = registro[1];
            BigDecimal preco = new BigDecimal(registro[2]);
            int quantidade = Integer.parseInt(registro[3]);

            Produto produtoBanco = new ProdutoBuilder()
                    .comNome(produto)
                    .comQuantidadeEstoque(Integer.parseInt((registro[3] + 1)))
                    .comPrecoUnitario(preco)
                    .comCategoria(categoriaBanco)
                    .build();

            em.flush();

            String nomeDoCliente = registro[5];

            ClienteDAO clienteDAO = new ClienteDAO(em);
            Cliente clienteBanco = clienteDAO.listaUnicoClientePorNome(nomeDoCliente);
            if (clienteBanco.getNome() == null) {
                clienteBanco = new ClienteBuilder()
                        .comNome(nomeDoCliente)
                        .comCpf("cpf")
                        .comTelefone("telefone")
                        .comEndereco(
                                new EnderecoBuilder()
                                        .comRua("rua")
                                        .comNumero(56)
                                        .comBairro("bairro")
                                        .comCidade("cidade")
                                        .comEstado("estado")
                                        .build())
                        .build();

                em.flush();

            }

            LocalDate data = LocalDate.parse(
                    registro[4],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Pedido pedidoBanco = new PedidoBuilder()
                    .comCliente(clienteBanco)
                    .comData(LocalDate.now())
                    .comDesconto(new BigDecimal(0.00))
                    .comTipoDescontoPedido(TipoDescontoPedido.NENHUM)
                    .comData(data)
                    .build();

            em.flush();

            ItemPedido itemPedidoBanco = new ItemPedidoBuilder()
                    .comPedido(pedidoBanco)
                    .comDesconto(pedidoBanco.getDesconto())
                    .comProduto(produtoBanco)
                    .comQuantidade(quantidade)
                    .comPrecoUnitario(produtoBanco.getPrecoUnitario())
                    .build();
            pedidoBanco.adicionarItemPedido(itemPedidoBanco);

            em.persist(categoriaBanco);
            em.persist(produtoBanco);
            em.persist(clienteBanco);
            em.persist(pedidoBanco);
            em.persist(itemPedidoBanco);

            em.getTransaction().commit();
            em.close();
        }
    }
}