package br.com.alura.comex.infra.cliente;

import java.net.URI;
import java.util.Optional;

import br.com.alura.comex.dominio.cliente.Cliente;
import br.com.alura.comex.infra.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepositoryComJPA clienteRepository;

    @GetMapping
    public Page<ClienteDto> listar(@RequestParam(required = false) Optional<Integer> pagina) {
        Integer paginaCorreta = 0;
        if (pagina.isPresent()) {
            paginaCorreta = pagina.get();
        }

        Pageable pagination = PageRequest.of(paginaCorreta, 5);
        Page<Cliente> clientes = clienteRepository.findAll(pagination);
        return ClienteDto.converter(clientes);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> salvar(@RequestBody ClienteForm clienteForm, UriComponentsBuilder uriBuilder,
            UsuarioRepository usuarioRepository) {
        Cliente cliente = clienteForm.converter(usuarioRepository);
        clienteRepository.save(cliente);

        URI uri = uriBuilder
                .path("/clientes/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ClienteDto(cliente));
    }

}
