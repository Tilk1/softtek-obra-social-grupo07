package prueba.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import prueba.model.Usuario;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Usuario buscarUsuarioPorEmail(String email) {
        return find("email", email).firstResult();
    }

}
