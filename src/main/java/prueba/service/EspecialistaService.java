package prueba.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import prueba.repository.EspecialistaRepository;
import prueba.DTO.EspecialistaDTO;
import prueba.model.Especialista;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;

@ApplicationScoped
public class EspecialistaService {
    
    @Inject
    EspecialistaRepository especialistaRepository;

    public List<Especialista> obtenerEspecialistas(){
        return especialistaRepository.findAllOrderedById();
    }

    public Especialista obtenerEspecialistaPorId(Long id){
        return especialistaRepository.findById(id);
    }

    public void crearEspecialista(EspecialistaDTO especialista){
        Especialista nuevoEspecialista = new Especialista();

        nuevoEspecialista.setNombre(especialista.getNombre());
        nuevoEspecialista.setHorariosConsulta(especialista.getHorariosConsulta());
        nuevoEspecialista.setUbicacion(especialista.getUbicacion());
        nuevoEspecialista.setEspecialidad(especialista.getEspecialidad());

        especialistaRepository.persist(nuevoEspecialista);
    }

    public void actualizarEspecialista(Long id, EspecialistaDTO especialista){
        Especialista especialistaActual = especialistaRepository.findById(id);
        if(especialistaActual == null){
            throw new IllegalArgumentException("El especialista no existe: " + id);
        }
        // Podriamos colocar un mapper para cada uno para evitar setear a mano
        especialistaActual.setNombre(especialista.getNombre());
        especialistaActual.setEspecialidad(especialista.getEspecialidad());
        especialistaActual.setUbicacion(especialista.getUbicacion());
        especialistaActual.setHorariosConsulta(especialista.getHorariosConsulta());
    }

public List<String> obtenerEspecialidades() {
    List<String> especialidades = especialistaRepository.findEspecialidades();
    Set<String> especialidadesUnicas = new HashSet<>(especialidades);
    return especialidadesUnicas.stream().collect(Collectors.toList());
}
}
