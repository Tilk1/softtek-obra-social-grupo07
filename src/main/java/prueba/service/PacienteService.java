package prueba.service;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import prueba.DTO.PacienteDTO;
import prueba.model.Paciente;
import prueba.model.Turno;
import prueba.repository.PacienteRepository;
import prueba.security.TokenService;

@ApplicationScoped
public class PacienteService {

    @Inject
    TokenService service;

    @Inject
    PacienteRepository pacienteRepository;

    @Inject
    JWTParser jwtParser;

    private static final Logger logger = Logger.getLogger(PacienteService.class.getName());

    public List<Paciente> obtenerPacientes() {
        return pacienteRepository.findAll().list();
    }

    public Paciente obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente obtenerPacientePorEmail(String email) {
        return pacienteRepository.find("email", email).firstResult();
    }

    public void crearPaciente(PacienteDTO paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no valido");
        }
        Paciente nuevaPaciente = new Paciente();
        nuevaPaciente.setNombre(paciente.getNombre());
        nuevaPaciente.setDni(paciente.getDni());
        nuevaPaciente.setNumeroCelular(paciente.getNumeroCelular());
        pacienteRepository.persist(nuevaPaciente);
    }

    public void actualizarPaciente(Long id, PacienteDTO paciente) {
        Paciente pacienteActual = pacienteRepository.findById(id);

        if (paciente.getNombre() != null || paciente.getNombre() != "") {
            pacienteActual.setNombre(paciente.getNombre());
        }
        if (paciente.getDni() != null || paciente.getDni() != "") {
            pacienteActual.setDni(paciente.getDni());
        }
        if (paciente.getNumeroCelular() != null || paciente.getNumeroCelular() != "") {
            pacienteActual.setNumeroCelular(paciente.getNumeroCelular());
        }
        pacienteRepository.persistAndFlush(pacienteActual);
    }

    public List<Turno> misTurnos(String token) {
        try {
            JsonWebToken jwt = jwtParser.parse(token);
            String jwtSub = jwt.getSubject();
            logger.info("Extracted JWT sub: " + jwtSub);

            Paciente paciente = pacienteRepository.find("email", jwtSub).firstResult();

            if (paciente != null) {
                return paciente.getTurnos();
            } else {
                logger.warning("Paciente no encontrado con email: " + jwtSub);
                return null;
            }
        } catch (ParseException e) {
            logger.severe("Token parsing issue: " + e.getMessage());
            throw new IllegalStateException("No se pudo procesar el token de autenticación.", e);
        }
    }
}
