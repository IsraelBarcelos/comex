package br.com.alura.services.processadores;

import br.com.alura.comex.Pedido;
import br.com.alura.comex.Produto;
import br.com.alura.services.processadores.component.PedidoJson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProcessadorDeJSON extends Processador {

  public ProcessadorDeJSON(String path) {
    super(path);
  }

  public void execute() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    List<PedidoJson> pedidosCompletos = objectMapper.readValue(
      new File("src\\main\\resources\\pedidos.json"),
      new TypeReference<List<PedidoJson>>() {}
    );

    montarPedidos(pedidosCompletos);
  }

  private void montarPedidos(List<PedidoJson> pedidosCompletos) {
    pedidosCompletos.forEach( //.stream()
      pedido -> {
        this.clientePlaceholder =
          this.clientesDoSistema.adicionaNovoClienteOuRejeita(
              pedido.getCliente()
            );

        Pedido pedidoMontado = new Pedido(
          new Produto(
            pedido.getProduto(),
            pedido.getCategoria(),
            pedido.getPreco()
          ),
          clientePlaceholder,
          pedido.getQuantidade(),
          pedido.getData()
        );
        this.pedidos.add(pedidoMontado);
      }
    );
  }
}
