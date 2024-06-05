package prueba.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import prueba.model.Receta;

@ApplicationScoped
public class RecetaRepository implements PanacheRepository<Receta> {

    public Receta obtenerPorIdTurno(Long id){
        Receta receta = find("turno.id",id).firstResult();        
        return receta;
    }
}
