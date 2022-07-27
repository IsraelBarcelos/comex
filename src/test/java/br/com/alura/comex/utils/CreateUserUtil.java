package br.com.alura.comex.utils;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.alura.comex.comercial.aplicacao.usuario.UsuarioBuilder;
import br.com.alura.comex.comercial.dominio.usuario.Email;
import br.com.alura.comex.comercial.dominio.usuario.Perfil;
import br.com.alura.comex.comercial.dominio.usuario.Usuario;
import br.com.alura.comex.comercial.infra.usuario.PerfilRepository;
import br.com.alura.comex.comercial.infra.usuario.UsuarioRepositoryComJPA;

public class CreateUserUtil {

    public static final String email = "teste@teste.com";
    public static final String password = "123456";
    public static final String nomeDoUsuario = "usuarioDoBancoTeste";

    public static void createUser(UsuarioRepositoryComJPA usuarioRepository, PasswordEncoder passwordEncoder,
            PerfilRepository perfilRepository)
            throws Exception {

        if (!usuarioRepository.encontrarUsuarioPeloEmail(email).isPresent()) {

            Perfil perfil = getPerfil(perfilRepository);

            Usuario user = new UsuarioBuilder()
                    .comEmail(new Email(email))
                    .comSenha(passwordEncoder.encode(password))
                    .comNome(nomeDoUsuario)
                    .addPerfil(perfil)
                    .build();
            usuarioRepository.save(user);
        }

    }

    private static Perfil getPerfil(PerfilRepository perfilRepository) {
        Optional<Perfil> optionalPerfil = perfilRepository.getByNome("ROLE_ADMIN");

        if (!optionalPerfil.isPresent()) {
            Perfil perfil = new Perfil();
            perfil.setNome("ROLE_ADMIN");
            perfilRepository.save(perfil);

            return perfil;
        }

        return optionalPerfil.get();
    }

}
