package prueba.controller;

import java.util.List;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import prueba.DTO.TurnoDTO;
import prueba.model.Turno;
import prueba.service.TurnoService;

@Path("/turnos")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TurnoController {

    private static final Logger logger = Logger.getLogger(TurnoController.class.getName());

    @Inject
    TurnoService turnoService;

    @Transactional
    @POST
    public Response crearTurno(TurnoDTO turnoDTO) {
        try {
            turnoService.guardarTurno(turnoDTO);
            return Response.ok("Turno creado exitosamente").build();
        } catch (Exception e) {
            logger.severe("Error al guardar el turno: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Hubo un error al intentar crear el turno: " + e.getMessage()).build();
        }
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response cancelarTurno(@PathParam("id") Long id) {
        boolean isDeleted = turnoService.cancelarTurno(id);
        if (isDeleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response obtenerTodosLosTurnos() {
        try {
            List<Turno> turnos = turnoService.findAllOrderedById();
            return Response.ok(turnos).build();
        } catch (Exception e) {
            logger.severe("Error al obtener los turnos: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Hubo un error al intentar obtener los turnos: " + e.getMessage()).build();
        }
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response actualizarTurno(@PathParam("id") Long id, TurnoDTO turnoDTO) {
        try {
            boolean isUpdated = turnoService.actualizarTurno(id, turnoDTO);
            if (isUpdated) {
                return Response.ok("Turno actualizado exitosamente").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.severe("Error al actualizar el turno: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Hubo un error al intentar actualizar el turno: " + e.getMessage()).build();
        }
    }
}
