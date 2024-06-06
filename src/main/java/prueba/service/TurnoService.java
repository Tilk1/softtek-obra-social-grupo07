package prueba.service;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import prueba.DTO.TurnoDTO;
import prueba.model.Turno;
import prueba.repository.TurnoRepository;
import lombok.Data;

@ApplicationScoped
@Data
public class TurnoService {

    @Inject
    TurnoRepository turnoRepository;

    public List<Turno> findAllOrderedById() {
        return turnoRepository.findAllOrderedById();
    }

    public Turno findById(Long id) {
        return turnoRepository.findById(id);
    }

    public void guardarTurno(TurnoDTO turnoDTO) {
        Turno turno = new Turno(turnoDTO.getNombrePaciente(), turnoDTO.getDniPaciente(), turnoDTO.getIdMedicoEspecialista(),
                turnoDTO.getMotivoConsulta(), turnoDTO.getFechaHoraCita());
        turnoRepository.persist(turno);
    }

    public boolean cancelarTurno(Long id) {
        return turnoRepository.deleteById(id);
    }
}
