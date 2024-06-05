package prueba.service;

import java.util.List;

import jakarta.inject.Inject;
import prueba.DTO.RecetaDTO;
import prueba.model.Receta;
import prueba.model.Turno;
import prueba.repository.RecetaRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecetaService {

    @Inject
    RecetaRepository recetaRepository;

    @Inject
    TurnoService turnoService;

    public List<Receta> obtenerRecetas() {
        return this.recetaRepository.findAll().list();
    }

    public Receta obtenerRecetaPorId(Long id) {
        return recetaRepository.findById(id);
    }

    public Receta obtenerRecetaPorIdTurno(Long id) {
        return recetaRepository.obtenerPorIdTurno(id);
    }

    public void crearReceta(RecetaDTO receta) {
        Turno turnoAsociado = turnoService.findById(receta.getId_turno());
        if (turnoAsociado == null) {
            throw new IllegalArgumentException("No se encontro el turno asociado a la receta");
        }
        Receta nuevaReceta = new Receta();
        nuevaReceta.setDescripcion(receta.getDescripcion());
        nuevaReceta.setTurno(turnoAsociado);
        recetaRepository.persist(nuevaReceta);
    }

}
