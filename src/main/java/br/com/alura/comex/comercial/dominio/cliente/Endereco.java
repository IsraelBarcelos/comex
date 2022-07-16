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
public class Endereco {

  @Column(nullable = false)
  private String rua;

  @Column(nullable = false)
  private Integer numero;

  private String complemento;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false)
  private String cidade;

  @Column(nullable = false)
  private String estado;

}
