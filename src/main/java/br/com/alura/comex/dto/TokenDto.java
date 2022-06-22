package br.com.alura.comex.dto;

public class TokenDto {
    private String token;
    private String tipo;

    public TokenDto(String token, String string) {
        this.token = token;
        this.tipo = string;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

}
