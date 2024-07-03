package prueba.repository;

import prueba.model.Especialista;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class EspecialistaRepository implements PanacheRepository<Especialista> {

    @PersistenceContext
    EntityManager entityManager;

    public List<Especialista> findAllOrderedById() {
        return listAll(Sort.by("id"));
    }

    public Especialista findById(Long id) {
        return find("id", id).firstResult();
    }

    public List<String> findEspecialidades() {
        String jpql = "SELECT DISTINCT e.especialidad FROM Especialista e";
        return entityManager.createQuery(jpql, String.class).getResultList();
    }

}