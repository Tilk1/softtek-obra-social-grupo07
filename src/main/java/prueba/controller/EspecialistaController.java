package prueba.controller;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import prueba.model.Especialista;
import prueba.repository.EspecialistaRepository;

@Path("/especialistas")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EspecialistaController {

    @Inject
    EspecialistaRepository especialistaRepository;

    @GET
    public List<Especialista> getAllEspecialistas() {
        return especialistaRepository.findAllOrderedById();
    }
}