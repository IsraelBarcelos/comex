package br.com.alura.comex.comercial.dominio.cliente;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Telefone {
    @Column(nullable = false, name = "telefone")
    private Integer numeroTelefone;
    @Column(nullable = false)
    private Integer ddd;

    public Telefone(Integer ddd, Integer numeroTelefone) {

        if (ddd.toString().length() != 2 || numeroTelefone.toString().length() != 9) {
            throw new IllegalArgumentException("DDD ou número de telefone estão inválidos!");
        }

        this.numeroTelefone = numeroTelefone;
        this.ddd = ddd;
    }

    public String getNumeroComDDD() {
        return ddd + " " + numeroTelefone;
    }
}
