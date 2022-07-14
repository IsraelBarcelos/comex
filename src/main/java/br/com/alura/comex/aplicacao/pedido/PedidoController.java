package br.com.alura.comex.aplicacao.pedido;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.dominio.pedido.Pedido;
import br.com.alura.comex.infra.cliente.ClienteRepositoryComJPA;
import br.com.alura.comex.infra.pedido.PedidoRepository;
import br.com.alura.comex.infra.produto.ProdutoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepositoryComJPA clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    @Cacheable("pedidos")
    public ResponseEntity<Page<PedidoDto>> listar(@RequestParam Integer page) {
        Pageable pagination = PageRequest.of(page, 5, Sort.by("data").descending().and(Sort.by("cliente.nome")));
        Page<Pedido> pedidos = pedidoRepository.findAll(pagination);
        return ResponseEntity.ok(PedidoDto.converter(pedidos));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoDto> salvar(@RequestBody @Valid PedidoForm pedidoForm,
            UriComponentsBuilder uriBuilder) {

        Pedido pedido = pedidoForm.converter(clienteRepository, produtoRepository, pedidoRepository);
        pedidoRepository.save(pedido);

        URI uri = uriBuilder
                .path("/pedidos/{id}")
                .buildAndExpand(pedido.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new PedidoDto(pedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoDePedidoDto> buscar(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            return ResponseEntity.ok(new DetalhamentoDePedidoDto(pedido.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/aW52YWxpZGEgcmVsYXTDs3JpbyBkZSB2ZW5kYXM")
    @CacheEvict("pedidos")
    public ResponseEntity<Object> invalidadaCache() {
        return ResponseEntity.ok().build();
    }
}
