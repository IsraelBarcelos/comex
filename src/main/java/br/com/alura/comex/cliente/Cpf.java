package br.com.alura.comex.cliente;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Cpf {

    private String numeroCpf;

    private String regexCpf = "([0-9]{2}\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})";

    private boolean numeroTemTamanhoValido = numeroCpf.length() == 11;
    private boolean numeroEstaNoPadraoCorreto = numeroCpf.matches(regexCpf);

    public Cpf(String numeroCpf) {
        if (numeroCpf == null || !numeroTemTamanhoValido || !numeroEstaNoPadraoCorreto) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.numeroCpf = numeroCpf;
    }
}
