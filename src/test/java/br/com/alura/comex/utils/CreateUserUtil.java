package br.com.alura.comex.utils;

import br.com.alura.comex.builders.UsuarioBuilder;
import br.com.alura.comex.models.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;

public class CreateUserUtil {
    public static void createUser(String email, String password, UsuarioRepository usuarioRepository) throws Exception {
        if (!usuarioRepository.findByEmail(email).isPresent()) {
            Usuario user = new UsuarioBuilder()
                    .comEmail(email)
                    .comSenha(password)
                    .comNome("usuarioDoBancoTeste")
                    .build();
            usuarioRepository.save(user);
        }
    }
}
