package br.com.alura.comex.controllers;

import br.com.alura.comex.controllers.form.ProdutoForm;
import br.com.alura.comex.dto.ProdutoDto;
import br.com.alura.comex.models.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  @GetMapping
  public List<ProdutoDto> index() {
    List<Produto> produtos = produtoRepository.findAll();
    return ProdutoDto.converter(produtos);
  }

  @GetMapping("/{id}")
  public ProdutoDto show(Long id) {
    Produto produto = produtoRepository.findById(id).get();
    return new ProdutoDto(produto);
  }

  @PostMapping
  public ResponseEntity<ProdutoDto> cadastrar(
    @RequestBody ProdutoForm produtoForm,
    UriComponentsBuilder uriBuilder
  ) {
    Produto produto = produtoForm.converter(categoriaRepository);
    produtoRepository.save(produto);

    URI uri = uriBuilder
      .path("/produtos/{id}")
      .buildAndExpand(produto.getId())
      .toUri();

    return ResponseEntity.created(uri).body(new ProdutoDto(produto));
  }
}
