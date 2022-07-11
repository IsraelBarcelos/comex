package br.com.alura.comex.cliente;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Telefone {
    @Column(nullable = false)
    private Integer numeroTelefone;
    @Column(nullable = false)
    private Integer ddd;

    private boolean dddCom2Digitos = ddd.toString().length() == 2;
    private boolean numeroCom8Digitos = numeroTelefone.toString().length() == 9;

    public Telefone(Integer ddd, Integer numeroTelefone) {

        if (!dddCom2Digitos || !numeroCom8Digitos) {
            throw new IllegalArgumentException("DDD ou número de telefone estão inválidos!");
        }
        this.numeroTelefone = numeroTelefone;
        this.ddd = ddd;
    }

    public String getNumeroComDDD() {
        return ddd + " " + numeroTelefone;
    }
}
