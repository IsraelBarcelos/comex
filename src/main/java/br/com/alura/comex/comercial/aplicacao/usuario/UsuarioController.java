package br.com.alura.comex.comercial.aplicacao.usuario;

import java.util.Optional;

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
import br.com.alura.comex.comercial.infra.usuario.UsuarioRepositoryComJPA;

@RestController
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepositoryComJPA usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PerfilRepository perfilRepository;

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastraUsuario(@RequestBody @Valid UserForm userForm) {
        try {
            Usuario usuario = usuarioRepository.save(userForm.converter());
            userForm.setSenha(passwordEncoder.encode(userForm.getSenha()));

            return new ResponseEntity<>(UsuarioDto.converter(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping("/add-perfil")
    public ResponseEntity<UsuarioDto> addPerfil(@RequestBody @Valid AddProfileForm profileForm) {

        Optional<Usuario> usuario = usuarioRepository.encontrarUsuarioPeloEmail(profileForm.getEmail());

        if (!usuario.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Usuario usuarioPresente = usuario.get();

        Perfil perfil = profileForm.converter();
        perfilRepository.save(perfil);
        usuarioPresente.addPerfil(perfil);
        usuarioRepository.save(usuarioPresente);
        return new ResponseEntity<>(UsuarioDto.converter(usuarioPresente), HttpStatus.CREATED);

    }

}
