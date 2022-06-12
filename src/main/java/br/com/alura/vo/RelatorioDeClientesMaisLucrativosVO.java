package br.com.alura.vo;

import java.math.BigDecimal;

import br.com.alura.models.Cliente;

public class RelatorioDeClientesMaisLucrativosVO {

    private Cliente cliente;
    private BigDecimal valorTotal;

    public RelatorioDeClientesMaisLucrativosVO(Cliente cliente, BigDecimal valorTotal) {
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

}
