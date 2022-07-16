package br.com.alura.comex.comercial.aplicacao.categoria;

import br.com.alura.comex.comercial.aplicacao.pedido.PedidoPorCategoriaDto;
import br.com.alura.comex.comercial.dominio.categoria.Categoria;
import br.com.alura.comex.comercial.dominio.pedido.Pedido;
import br.com.alura.comex.comercial.infra.categoria.CategoriaRepository;
import br.com.alura.comex.comercial.infra.pedido.PedidoRepository;
import br.com.alura.comex.comercial.utils.IterableToArrayList;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  @Cacheable("categorias")
  public ResponseEntity<Page<CategoriaDto>> index() {
    Pageable pagination = PageRequest.of(0, 5);
    Page<Categoria> categorias = categoriaRepository.findAll(pagination);
    return ResponseEntity.ok(CategoriaDto.converter(categorias));
  }

  @PostMapping
  @Transactional
  @CacheEvict(value = { "categorias", "pedidosPorCategorias" }, allEntries = true)
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
  @Cacheable(value = "pedidosPorCategoria")
  public ResponseEntity<Map<String, PedidoPorCategoriaDto>> listarPedidosDeUmaCategoria() {
    List<Categoria> categorias = IterableToArrayList.execute(categoriaRepository.findAll());

    Map<String, List<Pedido>> pedidos = new HashMap<>();
    categorias.stream().forEach(categoria -> {
      pedidos.put(categoria.getNome(), pedidoRepository.findByItemPedidoProdutoCategoriaNome(categoria.getNome()));
    });
    return ResponseEntity.ok(PedidoPorCategoriaDto.converter(pedidos));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoriaDto> detalhar(@PathVariable Long id) {
    Optional<Categoria> categoria = categoriaRepository.findById(id);

    if (categoria.isPresent()) {
      return ResponseEntity.ok(new CategoriaDto(categoria.get()));
    }

    return ResponseEntity.notFound().build();
  }

  @PatchMapping("/{id}")
  @Transactional
  @CacheEvict(value = { "categorias", "pedidosPorCategorias" }, allEntries = true)
  public ResponseEntity<AtivaDesativaCategoriaDto> atualizar(@PathVariable Long id) {
    Optional<Categoria> categoria = categoriaRepository.findById(id);

    if (!categoria.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Categoria categoriaAlterada = categoria.get();
    categoriaAlterada.alteraAtivacao();

    return ResponseEntity.ok(new AtivaDesativaCategoriaDto(categoriaAlterada));
  }

  @DeleteMapping("/{id}")
  @Transactional
  @CacheEvict(value = { "categorias", "pedidosPorCategorias" }, allEntries = true)
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    Optional<Categoria> categoria = categoriaRepository.findById(id);

    if (!categoria.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    categoriaRepository.delete(categoria.get());
    return ResponseEntity.noContent().build();
  }
}
