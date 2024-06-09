package prueba.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import prueba.DTO.EspecialistaDTO;
import prueba.model.Especialista;
import prueba.service.EspecialistaService;

@Path("/especialistas")
@ApplicationScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EspecialistaController {

    @Context
    SecurityContext securityContext;

    @Inject
    EspecialistaService especialistaService;

    @GET
    @Path("/{id}")
    public Response obtenerEspecialistaPorId(@PathParam("id") Long id) {
        try {
            Especialista especialista = especialistaService.obtenerEspecialistaPorId(id);
            if (especialista == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"mesange\":\"Especialista no encontrado en la base de datos.\"}")
                        .build();
            }
            return Response.ok(especialista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno del servidor. Por favor, inténtelo de nuevo más tarde.")
                    .build();
        }
    }

    @Operation(summary = "Consultar cartilla de especialistas", description = "Este endpoint devolverá una lista de médicos especialistas disponibles.")
    @GET
    public List<Especialista> obtenerEspecialistas() {
        return especialistaService.obtenerEspecialistas();
    }

    @POST
    public Response crearEspecialista(EspecialistaDTO especialista) {
        try {
            especialistaService.crearEspecialista(especialista);
            return Response.status(Response.Status.CREATED)
                    .entity("{¡El especialista se creó con éxito!}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el especialista: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizarEspecialista(@PathParam("id") Long id, EspecialistaDTO especialista) {
        try {
            especialistaService.actualizarEspecialista(id, especialista);
            return Response.ok("Se actualizo exitosamente los datos").build();
        } catch (Exception e) {
            return Response.status(400, "Error al actualizar el Especialista: " + e.getMessage()).build();
        }
    }

}