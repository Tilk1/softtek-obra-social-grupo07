package prueba.service;

import java.util.List;

import jakarta.inject.Inject;
import prueba.DTO.PacienteDTO;
import prueba.model.Paciente;
import prueba.repository.PacienteRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PacienteService {

    @Inject
    PacienteRepository pacienteRepository;

    public List<Paciente> obtenerPacientes() {
        return pacienteRepository.findAll().list();
    }

    public Paciente obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id);
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

}
