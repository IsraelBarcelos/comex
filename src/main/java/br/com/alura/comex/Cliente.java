package br.com.alura.comex;

public class Cliente {

  private String nome;
  private int numeroDePedidos;

  public Cliente(String nome) {
    this.nome = nome;
    this.numeroDePedidos = 1;
  }

  public String getNome() {
    return nome;
  }

  public int getNumeroDePedidos() {
    return numeroDePedidos;
  }

  public void addPedido() {
    this.numeroDePedidos++;
  }
}
