package prueba.service;

import java.util.List;

import jakarta.inject.Inject;
import prueba.model.Receta;
import prueba.repository.RecetaRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecetaService {

    @Inject
    RecetaRepository recetaRepository;

    public List<Receta> obtenerRecetas() {
        return this.recetaRepository.findAll().list();
    } 

    public Receta obtenerRecetaPorId(Long id) {
        return recetaRepository.findById(id);
    }

    public Receta obtenerRecetaPorIdTurno(Long id) {
        return recetaRepository.obtenerPorIdTurno(id);
    }

    public void crearReceta(Receta receta) {
        recetaRepository.persist(receta);
    }

}
