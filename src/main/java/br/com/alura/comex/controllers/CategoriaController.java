package br.com.alura.comex.controllers;

import br.com.alura.comex.controllers.form.CategoriaForm;
import br.com.alura.comex.dto.CategoriaDto;
import br.com.alura.comex.models.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @GetMapping
  public Iterable<Categoria> listar() {
    return categoriaRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<CategoriaDto> salvar(
      @RequestBody CategoriaForm categoriaform,
      UriComponentsBuilder uriBuilder) {
    Categoria categoria = categoriaform.converter();
    categoriaRepository.save(categoria);

    URI uri = uriBuilder
        .path("/categorias/{id}")
        .buildAndExpand(categoria.getId())
        .toUri();

    return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
  }
}
