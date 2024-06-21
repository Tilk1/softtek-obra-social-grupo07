package prueba.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import prueba.model.Paciente;

@ApplicationScoped
public class PacienteRepository implements PanacheRepository<Paciente> {

    public Paciente buscarPacientePorEmail(String email) {
        return find("email", email).firstResult();
    }

    public Paciente findByDni(String dniPaciente) {
        return find("dni", dniPaciente).firstResult();
    }

}
