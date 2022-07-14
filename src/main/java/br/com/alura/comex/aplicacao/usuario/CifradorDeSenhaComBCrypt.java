package br.com.alura.comex.aplicacao.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.alura.comex.dominio.usuario.CrifradorDeSenha;

public class CifradorDeSenhaComBCrypt implements CrifradorDeSenha {

    @Autowired
    PasswordEncoder senha;

    @Override
    public String cifrarSenha(String senha) {
        return this.senha.encode(senha);
    }

    @Override
    public boolean validaSenhaCifrada(String senhaCifrada, String senha) {
        return this.cifrarSenha(senha).equals(senhaCifrada);
    }

}
