package prueba.service;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import prueba.DTO.TurnoDTO;
import prueba.model.Turno;
import prueba.repository.TurnoRepository;

@ApplicationScoped
public class TurnoService {

    @Inject
    TurnoRepository turnoRepository;

    public List<Turno> findAllOrderedById() {
        return this.turnoRepository.findAllOrderedById();
    }

    public Turno findById(Long id) {
        return this.turnoRepository.findById(id);
    }

    public void guardarTurno(TurnoDTO turnoDTO) {
        Turno turno = new Turno(turnoDTO.nombrePaciente, turnoDTO.dniPaciente, turnoDTO.idMedicoEspecialista,
                turnoDTO.motivoConsulta, turnoDTO.fechaHoraCita);
        this.turnoRepository.persist(turno);
    }

    public boolean cancelarTurno(Long id) {
        return this.turnoRepository.deleteById(id);
    }
}
