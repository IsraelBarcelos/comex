package br.com.alura.comex.comercial.dominio.pedido;

import java.time.LocalDateTime;
import java.util.Map;

import br.com.alura.comex.shared.dominio.Evento;
import br.com.alura.comex.shared.dominio.TipoDeEvento;

public class PedidoCriado implements Evento {
    private final Pedido pedido;
    private final LocalDateTime momento;

    public PedidoCriado(Pedido pedido) {
        this.pedido = pedido;
        this.momento = LocalDateTime.now();
    }

    public Pedido getPedido() {
        return pedido;
    }

    @Override
    public LocalDateTime momento() {
        return momento;
    }

    @Override
    public TipoDeEvento tipo() {
        return TipoDeEvento.PEDIDO_CRIADO;
    }

    @Override
    public Map<String, Object> informacoes() {
        return Map.of("pedido", this.pedido.getId(),
                "cpf", this.pedido.getCliente().getCpf());
    }

}
