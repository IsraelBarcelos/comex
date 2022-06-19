package br.com.alura.comex.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.controllers.form.ClienteForm;
import br.com.alura.comex.dto.ClienteDto;
import br.com.alura.comex.models.Cliente;
import br.com.alura.comex.repository.ClienteRepository;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<ClienteDto> listar() {
        List<Cliente> clienteIterableToList = new ArrayList<>();
        clienteRepository.findAll().forEach(cliente -> {
            clienteIterableToList.add(cliente);
        });

        List<ClienteDto> clientes = clienteIterableToList.stream()
                .sorted((cliente1, cliente2) -> {
                    return cliente1.getNome().toLowerCase().compareTo(cliente2.getNome().toLowerCase());
                }).map(ClienteDto::new)
                .collect(Collectors.toList());
        return clientes;
    }

    @PostMapping
    public ResponseEntity<ClienteDto> salvar(@RequestBody ClienteForm clienteForm, UriComponentsBuilder uriBuilder) {
        Cliente cliente = clienteForm.converter();
        clienteRepository.save(cliente);

        URI uri = uriBuilder
                .path("/clientes/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ClienteDto(cliente));
    }

}
