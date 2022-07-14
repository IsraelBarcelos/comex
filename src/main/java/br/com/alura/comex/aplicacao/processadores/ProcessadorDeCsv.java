package br.com.alura.comex.aplicacao.processadores;
// package br.com.alura.comex.processadores;

// import br.com.alura.comex.builders.CategoriaBuilder;
// import br.com.alura.comex.builders.ClienteBuilder;
// import br.com.alura.comex.builders.EnderecoBuilder;
// import br.com.alura.comex.builders.ItemPedidoBuilder;
// import br.com.alura.comex.builders.PedidoBuilder;
// import br.com.alura.comex.builders.ProdutoBuilder;
// import br.com.alura.comex.models.Categoria;
// import br.com.alura.comex.models.Cliente;
// import br.com.alura.comex.models.ItemPedido;
// import br.com.alura.comex.models.Pedido;
// import br.com.alura.comex.models.Produto;
// import br.com.alura.comex.repository.CategoriaRepository;
// import br.com.alura.comex.repository.ClienteRepository;
// import br.com.alura.comex.repository.PedidoRepository;
// import br.com.alura.comex.repository.ProdutoRepository;
// import java.io.IOException;
// import java.math.BigDecimal;
// import java.net.URISyntaxException;
// import java.net.URL;
// import java.nio.file.Path;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.Scanner;
// import org.springframework.beans.factory.annotation.Autowired;

// public class ProcessadorDeCsv implements ProcessadorInterface {

// private String path;

// @Autowired
// private ProdutoRepository produtoRepository;

// @Autowired
// private CategoriaRepository categoriaRepository;

// @Autowired
// private ClienteRepository clienteRepository;

// private PedidoRepository pedidoRepository;

// public ProcessadorDeCsv(String path) {
// this.path = path;
// }

// public void execute() throws IOException, URISyntaxException {
// URL recursoCSV = ClassLoader.getSystemResource(this.path);

// if (recursoCSV == null) {
// throw new IllegalArgumentException("Arquivo não encontrado");
// }

// Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

// Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);

// leitorDeLinhas.nextLine();

// this.transformarDadosEmLinhas(leitorDeLinhas);

// leitorDeLinhas.close();
// }

// private void transformarDadosEmLinhas(Scanner leitorDeLinhas) {
// while (leitorDeLinhas.hasNextLine()) {
// String linha = leitorDeLinhas.nextLine();
// String[] registro = linha.split(",");

// String categoria = registro[0];

// Categoria categoriaBanco = salvaCategoriaNoBanco(categoria);

// String produto = registro[1];
// BigDecimal preco = new BigDecimal(registro[2]);
// int quantidade = Integer.parseInt(registro[3]);

// Produto produtoBanco = salvaProdutoNoBanco(
// registro,
// categoriaBanco,
// produto,
// preco);

// String nomeDoCliente = registro[5];

// Cliente clienteBanco = salvaClienteNoBanco(nomeDoCliente);

// LocalDateTime data = LocalDateTime.parse(
// registro[4],
// DateTimeFormatter.ofPattern("dd/MM/yyyy"));
// salvarItensEPedidosNoBanco(quantidade, produtoBanco, clienteBanco, data);
// }
// }

// private void salvarItensEPedidosNoBanco(
// int quantidade,
// Produto produtoBanco,
// Cliente clienteBanco,
// LocalDateTime data) {
// Pedido pedidoBanco = new PedidoBuilder()
// .comData(data)
// .comCliente(clienteBanco)
// .comDesconto(pedidoRepository)
// .build();

// ItemPedido itemPedidoBanco = new ItemPedidoBuilder()
// .comDesconto()
// .comProduto(produtoBanco)
// .comQuantidade(quantidade)
// .comPrecoUnitario(produtoBanco.getPrecoUnitario())
// .build();
// pedidoBanco.adicionarItemPedido(itemPedidoBanco);
// pedidoRepository.save(pedidoBanco);
// }

// private Cliente salvaClienteNoBanco(String nomeDoCliente) {
// Cliente clienteBanco = clienteRepository.findByNome(nomeDoCliente).get();
// if (clienteBanco.getNome() == null) {
// clienteBanco = new ClienteBuilder()
// .comNome(nomeDoCliente)
// .comCpf("cpf")
// .comTelefone("telefone")
// .comEndereco(
// new EnderecoBuilder()
// .comRua("rua")
// .comNumero(56)
// .comBairro("bairro")
// .comCidade("cidade")
// .comEstado("estado")
// .build())
// .build();

// clienteRepository.save(clienteBanco);
// }
// return clienteBanco;
// }

// private Produto salvaProdutoNoBanco(
// String[] registro,
// Categoria categoriaBanco,
// String produto,
// BigDecimal preco) {
// Produto produtoBanco = new ProdutoBuilder()
// .comNome(produto)
// .comQuantidadeEstoque(Integer.parseInt((registro[3] + 1)))
// .comPrecoUnitario(preco)
// .comCategoria(categoriaBanco)
// .build();

// produtoRepository.save(produtoBanco);
// return produtoBanco;
// }

// private Categoria salvaCategoriaNoBanco(String categoria) {
// Categoria categoriaBanco = new CategoriaBuilder()
// .comNome(categoria)
// .ativaCategoria()
// .build();

// categoriaRepository.save(categoriaBanco);
// return categoriaBanco;
// }
// }
