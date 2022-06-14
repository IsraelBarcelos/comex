package br.com.alura.comex.controllers;

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

import br.com.alura.comex.controllers.form.PedidoForm;
import br.com.alura.comex.dto.PedidoDto;
import br.com.alura.comex.models.Pedido;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<PedidoDto> listar() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return PedidoDto.converter(pedidos);
    }

    @PostMapping
    public ResponseEntity<PedidoDto> salvar(@RequestBody PedidoForm pedidoForm, UriComponentsBuilder uriBuilder) {

        Pedido pedido = pedidoForm.converter(clienteRepository);
        pedidoRepository.save(pedido);
        pedido.getItensPedido().forEach(item -> item.getProduto()
                .setQuantidadeEstoque(item.getProduto().getQuantidadeEstoque() - item.getQuantidade()));

        URI uri = uriBuilder
                .path("/pedidos/{id}")
                .buildAndExpand(pedido.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new PedidoDto(pedido));
    }
}
