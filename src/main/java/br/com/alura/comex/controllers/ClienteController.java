package br.com.alura.comex.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.controllers.form.ClienteForm;
import br.com.alura.comex.dto.ClienteDto;
import br.com.alura.comex.models.Cliente;
import br.com.alura.comex.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<ClienteDto> salvar(ClienteForm clienteForm, UriComponentsBuilder uriBuilder) {
        Cliente cliente = clienteForm.converter();
        clienteRepository.save(cliente);

        URI uri = uriBuilder
                .path("/clientes/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ClienteDto(cliente));
    }

}
