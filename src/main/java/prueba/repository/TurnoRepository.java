package prueba.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import prueba.DTO.TurnoDTO;
import prueba.model.Especialista;
import prueba.model.Turno;
import prueba.model.Receta;

import java.util.List;
import java.util.NoSuchElementException;

@ApplicationScoped
public class TurnoRepository implements PanacheRepository<Turno> {

    @Inject
    RecetaRepository recetaRepository;

    public List<Turno> findAllOrderedById() {
        return listAll(Sort.by("id"));
    }

    public Turno findById(Long id) {
        return find("id", id).firstResult();
    }

    @Transactional
    public void borrarTurnoById(Long id) {
        Turno turno = findById(id);
        if (turno == null) {
            throw new NoSuchElementException();
        }
        Receta receta = recetaRepository.findByTurno(turno);
        if (receta != null) {
            recetaRepository.delete(receta);
        }
        delete(turno);
    }

    @Transactional
    public void actualizarTurno(Long id, TurnoDTO turnoDTO) {
        Turno turno = findById(id);
        if (turno == null) {
            throw new NoSuchElementException();
        }
        turno.setFechaHoraCita(turnoDTO.getFechaHoraCita());
        turno.setMotivoConsulta(turnoDTO.getMotivoConsulta());
        if (turnoDTO.getIdMedicoEspecialista() != null) {
            turno.especialista = Especialista.findById(turnoDTO.getIdMedicoEspecialista());
        }
        persist(turno);
    }
}
