package br.com.alura.comex.shared.dominio;

import java.time.LocalDateTime;
import java.util.Map;

public interface Evento {

    LocalDateTime momento();

    TipoDeEvento tipo();

    Map<String, Object> informacoes();
}
