package br.com.alura.comex.comercial.aplicacao.usuario;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.comercial.dominio.usuario.Perfil;
import br.com.alura.comex.comercial.dominio.usuario.Usuario;
import br.com.alura.comex.comercial.infra.usuario.PerfilRepository;
import br.com.alura.comex.comercial.infra.usuario.UsuarioRepository;

@RestController
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PerfilRepository perfilRepository;

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

    @Transactional
    @PostMapping("/add-perfil")
    public ResponseEntity<UsuarioDto> addPerfil(@RequestBody @Valid AddProfileForm profileForm) {

        try {
            Usuario usuario = usuarioRepository.findByEmail(profileForm.getEmail()).get();
            Perfil perfil = profileForm.converter();
            perfilRepository.save(perfil);
            usuario.addPerfil(perfil);
            usuarioRepository.save(usuario);
            return new ResponseEntity<UsuarioDto>(UsuarioDto.converter(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<UsuarioDto>(HttpStatus.BAD_REQUEST);
        }

    }

}
