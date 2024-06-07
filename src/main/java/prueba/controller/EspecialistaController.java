package prueba.controller;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import prueba.model.Especialista;
import prueba.repository.EspecialistaRepository;
import prueba.security.Roles;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

@Path("/especialistas")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EspecialistaController {

    @Context
    SecurityContext securityContext;

    @Inject
    EspecialistaRepository especialistaRepository;

    @GET
    @RolesAllowed({ Roles.USER })
    public List<Especialista> getAllEspecialistas() {
        return especialistaRepository.findAllOrderedById();
    }
}