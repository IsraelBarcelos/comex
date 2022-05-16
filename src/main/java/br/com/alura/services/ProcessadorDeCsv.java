package br.com.alura.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.alura.comex.Pedido;


public class ProcessadorDeCsv implements ResgatarRelatóriosInterface {
    private String path;
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    public static int quantidadeDeRegistros;

    public ProcessadorDeCsv(String path) {
        this.path = path;
    }

    public void execute() {

        try {
            URL recursoCSV = ClassLoader.getSystemResource(this.path);
            Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

            Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);
            leitorDeLinhas.nextLine();

            quantidadeDeRegistros = this.getNumberOfRegisters(leitorDeLinhas);
            
            leitorDeLinhas.close();
        
        } catch (URISyntaxException e) {
            throw new RuntimeException("Arquivo pedido.csv não localizado!");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
        }
    
            
    }

    private int getNumberOfRegisters(Scanner leitorDeLinhas) {

        while (leitorDeLinhas.hasNextLine()) {
            String linha = leitorDeLinhas.nextLine();
            String[] registro = linha.split(",");

            String categoria = registro[0];
            String produto = registro[1];
            BigDecimal preco = new BigDecimal(registro[2]);
            int quantidade = Integer.parseInt(registro[3]);
            LocalDate data = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String cliente = registro[5];

            Pedido pedido = new Pedido(categoria, produto, cliente, preco, quantidade, data);
            pedidos.add(pedido);

            quantidadeDeRegistros++;
        }

        return quantidadeDeRegistros;
    }
    
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }


            
}
