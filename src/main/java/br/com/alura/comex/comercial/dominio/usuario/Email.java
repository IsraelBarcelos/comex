package br.com.alura.comex.comercial.dominio.usuario;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Email {

    private String endereco;

    private String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private boolean enderecoTemTamanhoValido = endereco.length() > 0;
    private boolean enderecoEstaNoPadraoCorreto = endereco.matches(regexEmail);

    public Email(String endereco) {
        if (endereco == null || !enderecoTemTamanhoValido || !enderecoEstaNoPadraoCorreto) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.endereco = endereco;
    }
}
