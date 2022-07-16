package br.com.alura.comex.comercial.aplicacao.pedido;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.alura.comex.comercial.dominio.pedido.Pedido;

public class PedidoPorCategoriaDto {
    private Integer quantidadeDeProdutosVendidos = 0;
    private BigDecimal montanteVendido = BigDecimal.ZERO;

    public PedidoPorCategoriaDto(List<Pedido> pedido) {

        this.quantidadeDeProdutosVendidos = pedido.size();
        this.montanteVendido = pedido.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Integer getQuantidadeDeProdutosVendidos() {
        return quantidadeDeProdutosVendidos;
    }

    public BigDecimal getMontanteVendido() {
        return montanteVendido;
    }

    public static Map<String, PedidoPorCategoriaDto> converter(Map<String, List<Pedido>> pedidos) {
        Map<String, PedidoPorCategoriaDto> returnMap = new HashMap<>();
        Set<String> nomesCategorias = pedidos.keySet();
        for (String nomeCategoria : nomesCategorias) {
            List<Pedido> pedidosParaCategoria = pedidos.get(nomeCategoria);
            returnMap.put(nomeCategoria, new PedidoPorCategoriaDto(pedidosParaCategoria));
        }

        return returnMap;
    }
}
