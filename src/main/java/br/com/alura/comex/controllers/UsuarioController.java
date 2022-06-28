package br.com.alura.comex.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.controllers.form.UserForm;
import br.com.alura.comex.dto.UsuarioDto;
import br.com.alura.comex.models.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;

@RestController
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastraUsuario(@RequestBody @Valid UserForm userForm) {

        try {
            userForm.setSenha(passwordEncoder.encode(userForm.getSenha()));
            Usuario usuario = usuarioRepository.save(userForm.converter());
            return new ResponseEntity<UsuarioDto>(UsuarioDto.converter(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<UsuarioDto>(HttpStatus.BAD_REQUEST);
        }

    }

}
