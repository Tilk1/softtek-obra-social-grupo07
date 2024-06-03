package prueba.repository;

import prueba.model.Especialista;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EspecialistaRepository implements PanacheRepository<Especialista> {

    public List<Especialista> findAllOrderedById() {
        return listAll(Sort.by("id"));
    }

    public Especialista findById(Long id) {
        return find("id", id).firstResult();
    }

}