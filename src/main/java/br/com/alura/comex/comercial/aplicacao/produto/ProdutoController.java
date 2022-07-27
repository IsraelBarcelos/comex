package br.com.alura.comex.comercial.aplicacao.produto;

import br.com.alura.comex.comercial.dominio.produto.Produto;
import br.com.alura.comex.comercial.infra.categoria.CategoriaRepositoryComJPA;
import br.com.alura.comex.comercial.infra.produto.ProdutoRepositoryComJPA;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepositoryComJPA produtoRepository;

    @Autowired
    private CategoriaRepositoryComJPA categoriaRepository;

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> listar(@RequestParam(required = false) Optional<Integer> pagina) {
        Integer paginaCorreta = 0;
        if (pagina.isPresent()) {
            paginaCorreta = pagina.get();
        }
        Pageable pagination = PageRequest.of(paginaCorreta, 5);

        Page<ProdutoDto> produtos = produtoRepository.listarTodosProdutos(pagination).map(ProdutoDto::new);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(
            @RequestBody @Valid ProdutoForm produtoForm,
            UriComponentsBuilder uriBuilder) {

        Produto produto = produtoForm.converter(categoriaRepository);
        produtoRepository.criarProduto(produto);

        URI uri = uriBuilder
                .path("/produtos/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ProdutoDto(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> detalhar(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.encontrarProdutoPeloId(id);

        if (produto.isPresent()) {
            return ResponseEntity.ok(new ProdutoDto(produto.get()));
        }

        return ResponseEntity.notFound().build();
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
        Optional<Produto> produto = produtoRepository.encontrarProdutoPeloId(id);

        if (produto.isPresent()) {
            produtoRepository.removerProduto(produto.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
