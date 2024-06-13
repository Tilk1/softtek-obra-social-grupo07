package prueba.service;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import prueba.DTO.TurnoDTO;
import prueba.model.Turno;
import prueba.repository.TurnoRepository;
import lombok.Data;
import prueba.model.Especialista;
import prueba.repository.EspecialistaRepository;

@ApplicationScoped
@Data
public class TurnoService {

    @Inject
    TurnoRepository turnoRepository;

    @Inject
    EspecialistaRepository especialistaRepository;

    public List<Turno> findAllOrderedById() {
        return turnoRepository.findAllOrderedById();
    }

    public Turno findById(Long id) {
        return turnoRepository.findById(id);
    }

    public void guardarTurno(TurnoDTO turnoDTO) {
        Especialista especialista = especialistaRepository.findById(turnoDTO.getIdMedicoEspecialista());
        if (especialista == null) {
            throw new IllegalArgumentException("No se encontró el especialista con el id proporcionado");
        }
        Turno turno = new Turno(turnoDTO.getNombrePaciente(), turnoDTO.getDniPaciente(), especialista,
                turnoDTO.getMotivoConsulta(), turnoDTO.getFechaHoraCita());
        turnoRepository.persist(turno);
    }

    public void cancelarTurno(Long id) {
        turnoRepository.borrarTurnoById(id);
    }

    public void actualizarTurno(Long id, TurnoDTO turnoDTO) {
        Especialista especialista = especialistaRepository.findById(turnoDTO.getIdMedicoEspecialista());
        if (especialista == null) {
            throw new IllegalArgumentException("No se encontró el especialista con el id proporcionado");
        }
        turnoRepository.actualizarTurno(id, turnoDTO);
    }
}
