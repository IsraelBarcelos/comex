package br.com.alura.comex.utils;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.alura.comex.dominio.pedido.ItemPedidoBuilder;
import br.com.alura.comex.dominio.pedido.Pedido;
import br.com.alura.comex.dominio.pedido.PedidoBuilder;
import br.com.alura.comex.infra.categoria.CategoriaRepository;
import br.com.alura.comex.infra.cliente.ClienteRepositoryComJPA;
import br.com.alura.comex.infra.pedido.PedidoRepository;
import br.com.alura.comex.infra.produto.ProdutoRepository;
import br.com.alura.comex.infra.usuario.PerfilRepository;
import br.com.alura.comex.infra.usuario.UsuarioRepository;

public class CreatePedidoUtil {
        public static void createPedido(ClienteRepositoryComJPA clienteRepository, PedidoRepository pedidoRepository,
                        UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository,
                        CategoriaRepository categoriaRepository, PasswordEncoder passwordEncoder,
                        PerfilRepository perfilRepository) throws Exception {
                if (pedidoRepository.findAll().isEmpty()) {
                        CreateClienteUtil.createCliente(clienteRepository, usuarioRepository, passwordEncoder,
                                        perfilRepository);
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
