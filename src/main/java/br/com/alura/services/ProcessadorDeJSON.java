package br.com.alura.services;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.comex.Cliente;
import br.com.alura.comex.ClientesDoSistema;
import br.com.alura.comex.Pedido;

public class ProcessadorDeJSON {

    private String categoria;
    String produto;
    BigDecimal preco;
    int quantidade;
    LocalDate data;
    String cliente;
    


    private String path;
  private ArrayList<Pedido> pedidos = new ArrayList<>();
  private ClientesDoSistema clientesDoSistema;
  private Cliente clientePlaceholder;

    ObjectMapper objectMapper = new ObjectMapper();
    String json = "<json object>";

    Product product = objectMapper.readValue(json, Product.class);


    public void execute() throws IOException, URISyntaxException {
        URL recursoCSV = ClassLoader.getSystemResource(this.path);
        Path caminhoDoArquivo = Path.of(recursoCSV.toURI());
    
        Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);
    
        leitorDeLinhas.nextLine();
    
        this.transformarDadosEmLinhas(leitorDeLinhas);
    
        leitorDeLinhas.close();
      }
    
      public ArrayList<Pedido> getPedidos() {
        return pedidos;
      }
    
      public ClientesDoSistema getClientesDoSistema() {
        return clientesDoSistema;
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
