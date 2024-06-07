package prueba.service;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import prueba.repository.UsuarioRepository;
import prueba.model.Usuario;
import prueba.security.TokenService;

@ApplicationScoped
public class AuthService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    TokenService service;

    public String login(String mailString, String password) {
        if (mailString == null || password == null) {
            throw new IllegalArgumentException("Mail and password are required");
        }
        if (mailString.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Mail and password are required");
        }

        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail(mailString);

        if (usuario == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!usuario.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        return service.generateUserToken(mailString, password);

    }

}
