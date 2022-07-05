package br.com.alura.comex.utils;

import java.time.LocalDateTime;

import br.com.alura.comex.builders.ItemPedidoBuilder;
import br.com.alura.comex.builders.PedidoBuilder;
import br.com.alura.comex.models.Pedido;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import br.com.alura.comex.repository.UsuarioRepository;

public class CreatePedidoUtil {
    public static void createPedido(ClienteRepository clienteRepository, PedidoRepository pedidoRepository,
            UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository) throws Exception {
        if (pedidoRepository.findAll().isEmpty()) {
            CreateClienteUtil.createCliente(clienteRepository, usuarioRepository);
            CreateProdutoUtil.createProduto(produtoRepository, categoriaRepository);

            Pedido pedido = new PedidoBuilder().comCliente(clienteRepository
                    .findByNome(CreateClienteUtil.nome).get())
                    .comData(LocalDateTime.now())
                    .comDesconto(pedidoRepository)
                    .build();

            pedido.adicionarItemPedido(new ItemPedidoBuilder()
                    .comProduto(produtoRepository
                            .findByNome(CreateProdutoUtil.nome).get())
                    .comQuantidade(1)
                    .comPrecoUnitario(CreateProdutoUtil.valor)
                    .comDesconto()
                    .build());

            pedidoRepository.save(pedido);
        }
    }
}
