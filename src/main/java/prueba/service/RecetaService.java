package prueba.service;

import java.util.List;
import java.util.Optional;

import jakarta.inject.Inject;
import prueba.model.Receta;
import prueba.repository.RecetaRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecetaService {

    @Inject
    RecetaRepository recetaRepository;

    public List<Receta> getAll() {
        return this.recetaRepository.findAll().list();
    } 

    public Receta getByTurnoId(Long id) {
        List<Receta> recetas = getAll();
        Optional<Receta> receta = recetas.stream()
                                    .filter(r -> r.getIdTurno().equals(id))
                                        .findFirst();

        return receta.orElse(null); // Si no se encuentra la receta, retorna null
    }

    public void save(Receta receta) {
        recetaRepository.persist(receta);
    }

}
