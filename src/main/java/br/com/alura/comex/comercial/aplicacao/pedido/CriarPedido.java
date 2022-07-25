package br.com.alura.comex.comercial.aplicacao.pedido;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.comex.comercial.dominio.pedido.Pedido;
import br.com.alura.comex.comercial.dominio.pedido.PedidoCriado;
import br.com.alura.comex.comercial.dominio.pedido.RepositorioDePedido;

import br.com.alura.comex.comercial.infra.cliente.ClienteRepositoryComJPA;
import br.com.alura.comex.comercial.infra.pedido.PedidoRepositoryComJPA;
import br.com.alura.comex.comercial.infra.produto.ProdutoRepository;

import br.com.alura.comex.shared.dominio.PublicadorDeEventos;

public class CriarPedido {

    private final RepositorioDePedido repositorioDePedido;
    private final PublicadorDeEventos publicadorDeEventos;

    @Autowired
    ClienteRepositoryComJPA clienteRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PedidoRepositoryComJPA pedidoRepository;

    public CriarPedido(RepositorioDePedido repositorioDePedido,
            PublicadorDeEventos publicadorDeEventos) {
        this.repositorioDePedido = repositorioDePedido;
        this.publicadorDeEventos = publicadorDeEventos;

    }

    public void executa(PedidoForm form) {
        Pedido pedido = form.converter(clienteRepository, produtoRepository,
                pedidoRepository);
        repositorioDePedido.criarPedido(pedido);
        PedidoCriado pedidoCriado = new PedidoCriado(pedido);
        publicadorDeEventos.publicar(pedidoCriado);
    }

}
