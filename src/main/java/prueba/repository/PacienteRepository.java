package prueba.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import prueba.model.Paciente;

@ApplicationScoped
public class PacienteRepository implements PanacheRepository<Paciente> {

    public Paciente buscarPacientePorEmail(String email) {
        return find("email", email).firstResult();
    }
}
