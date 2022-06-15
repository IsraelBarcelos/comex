package br.com.alura.comex.controllers;

import br.com.alura.comex.controllers.form.AtualizacaoProdutoForm;
import br.com.alura.comex.controllers.form.ProdutoForm;
import br.com.alura.comex.dto.ProdutoDto;
import br.com.alura.comex.models.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/produtos")
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

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(
            @RequestBody @Valid ProdutoForm produtoForm,
            UriComponentsBuilder uriBuilder) {

        Produto produto = produtoForm.converter(categoriaRepository);
        produtoRepository.save(produto);

        URI uri = uriBuilder
                .path("/produtos/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ProdutoDto(produto));
    }

    @GetMapping("/{id}")
    public ProdutoDto detalhar(@PathVariable Long id) {
        Produto produto = produtoRepository.getReferenceById(id);
        return new ProdutoDto(produto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id,
            @RequestBody @Valid AtualizacaoProdutoForm produtoForm) {
        Produto produto = produtoForm.atualizar(id, produtoRepository);

        return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
