package br.com.alura.comex.controllers;

import br.com.alura.comex.controllers.form.AtivaDesativaCategoriaDto;
import br.com.alura.comex.controllers.form.CategoriaForm;
import br.com.alura.comex.dto.CategoriaDto;
import br.com.alura.comex.dto.PedidoPorCategoriaDto;
import br.com.alura.comex.models.Categoria;
import br.com.alura.comex.models.Pedido;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.utils.IterableToArrayList;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    List<CategoriaDto> categorias = IterableToArrayList.execute(categoriaRepository.findAll()).stream()
        .map(CategoriaDto::new)
        .collect(Collectors.toList());
    return ResponseEntity.ok(categorias);
  }

  @PostMapping
  @Transactional
  public ResponseEntity<CategoriaDto> salvar(
      @RequestBody CategoriaForm categoriaform,
      UriComponentsBuilder uriBuilder) {
    Categoria categoria = categoriaform.converter();
    categoriaRepository.save(categoria);

    URI uri = uriBuilder
        .path("/api/categorias/{id}")
        .buildAndExpand(categoria.getId())
        .toUri();

    return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
  }

  @GetMapping("/pedidos")
  public ResponseEntity<Map<String, PedidoPorCategoriaDto>> listarPedidosDeUmaCategoria() {
    List<Categoria> categorias = IterableToArrayList.execute(categoriaRepository.findAll());

    Map<String, List<Pedido>> pedidos = new HashMap<>();
    categorias.stream().forEach(categoria -> {
      pedidos.put(categoria.getNome(), pedidoRepository.findByItemPedidoProdutoCategoriaNome(categoria.getNome()));
    });
    return ResponseEntity.ok(PedidoPorCategoriaDto.converter(pedidos));
  }

  @PatchMapping("/{id}")
  @Transactional
  public ResponseEntity<AtivaDesativaCategoriaDto> atualizar(@PathVariable Long id) {
    Optional<Categoria> categoria = categoriaRepository.findById(id);

    if (!categoria.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Categoria categoriaAlterada = categoria.get();
    categoriaAlterada.alteraAtivacao();

    return ResponseEntity.ok(new AtivaDesativaCategoriaDto(categoriaAlterada));
  }

}
