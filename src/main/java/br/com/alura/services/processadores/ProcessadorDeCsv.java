package br.com.alura.services.processadores;

import br.com.alura.comex.Pedido;
import br.com.alura.comex.Produto;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ProcessadorDeCsv extends Processador {

  public ProcessadorDeCsv(String path) {
    super(path);
  }

  public void execute() throws IOException, URISyntaxException {
    URL recursoCSV = ClassLoader.getSystemResource(this.path);
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

      String categoria = registro[0];
      String produto = registro[1];
      BigDecimal preco = new BigDecimal(registro[2]);
      int quantidade = Integer.parseInt(registro[3]);
      LocalDate data = LocalDate.parse(
        registro[4],
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
      );
      String nomeDoCliente = registro[5];

      this.clientePlaceholder =
        this.clientesDoSistema.adicionaNovoClienteOuRejeita(nomeDoCliente);

      Pedido pedido = new Pedido(
        new Produto(produto, categoria, preco),
        clientePlaceholder,
        quantidade,
        data
      );
      pedidos.add(pedido);
    }
  }
}
