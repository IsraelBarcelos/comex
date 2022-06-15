package br.com.alura.comex.controllers;

import br.com.alura.comex.controllers.form.CategoriaForm;
import br.com.alura.comex.dto.CategoriaDto;
import br.com.alura.comex.dto.PedidoPorCategoriaDto;
import br.com.alura.comex.models.Categoria;
import br.com.alura.comex.models.Pedido;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.PedidoRepository;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  @Autowired
  private PedidoRepository pedidoRepository;

  @GetMapping
  public ResponseEntity<List<CategoriaDto>> index() {
    List<Categoria> categorias = categoriaRepository.findAll();
    return ResponseEntity.ok(CategoriaDto.converter(categorias));
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

  @GetMapping("/pedidos")
  public ResponseEntity<Map<String, PedidoPorCategoriaDto>> listarPedidosDeUmaCategoria() {
    List<Categoria> categorias = categoriaRepository.findAll();

    Map<String, List<Pedido>> pedidos = new HashMap<>();
    categorias.stream().forEach(categoria -> {
      pedidos.put(categoria.getNome(), pedidoRepository.findByItemPedidoProdutoCategoriaNome(categoria.getNome()));
    });
    // categoriaRepository.findPedidosDeUmaCategoria();
    return ResponseEntity.ok(PedidoPorCategoriaDto.converter(pedidos));
  }

}
