package br.com.alura.comex.shared.dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Cpf {

    @Column(name = "cpf")
    private String numeroCpf;

    @Transient
    private String regexCpf = "([0-9]{2}\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})";

    public Cpf(String numeroCpf) {
        if (numeroCpf == null || numeroCpf.length() != 11 || !(numeroCpf.matches(regexCpf))) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.numeroCpf = numeroCpf;
    }
}
