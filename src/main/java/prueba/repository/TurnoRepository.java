package prueba.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import prueba.model.Turno;

import java.util.List;

@ApplicationScoped
public class TurnoRepository implements PanacheRepository<Turno> {

    public List<Turno> findAllOrderedById() {
        return listAll(Sort.by("id"));
    }

    public Turno findById(Long id) {
        return find("id", id).firstResult();
    }

    public void guardarTurno(Turno turno) {
        persist(turno);
    }

    @Transactional
    public boolean deleteById(Long id) {
        Turno turno = findById(id);
        if (turno != null) {
            delete(turno);
            return true;
        } else {
            return false;
        }
    }
}
