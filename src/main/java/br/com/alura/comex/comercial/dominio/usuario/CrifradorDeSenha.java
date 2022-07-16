package br.com.alura.comex.comercial.dominio.usuario;

public interface CrifradorDeSenha {
    public String cifrarSenha(String senha);

    public boolean validaSenhaCifrada(String senhaCifrada, String senha);
}
