package br.com.alura.comex.comercial.dominio.usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Email {

    @Column(name = "email", nullable = false)
    private String endereco;

    private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public Email(String endereco) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(endereco);
        if (endereco == null) {
            throw new IllegalArgumentException("Email está nulo");
        }

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Email inválido");
        }

        this.endereco = endereco;

    }
}
