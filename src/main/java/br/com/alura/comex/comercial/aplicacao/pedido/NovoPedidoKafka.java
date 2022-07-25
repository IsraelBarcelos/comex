package br.com.alura.comex.comercial.aplicacao.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class NovoPedidoKafka {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void enviaMensagemPedidoCriado() {
        kafkaTemplate.send("LOJA_NOVO_PEDIDO", "Pedido criado");
    }
}
