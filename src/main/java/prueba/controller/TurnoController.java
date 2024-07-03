package prueba.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.eclipse.microprofile.openapi.annotations.Operation;

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
    @Operation(summary = "Crear turno medico", description = "Este endpoint permitirá a los usuarios crear un nuevo turno medico")
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
    @Operation(summary = "Eliminar turno médico", description = "Este endpoint permitirá a los usuarios cancelar un turno médico existente identificado por su ID. No se requerirá enviar ningún dato en el cuerpo de la solicitud.")
    @DELETE
    @Path("/{id}")
    public Response cancelarTurno(@PathParam("id") Long id) {
        try {
            turnoService.cancelarTurno(id);
            return Response.noContent().build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            logger.severe("Error al cancelar el turno: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Hubo un error al intentar cancelar el turno: " + e.getMessage()).build();
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

    @GET
    @Path("/{id}")
    public Response obtenerTurnoPorId(@PathParam("id") Long id) {
        try {
            Turno turno = turnoService.findById(id);
            if (turno != null) {
                return Response.ok(turno).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.severe("Error al obtener el turno: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Hubo un error al intentar obtener el turno: " + e.getMessage()).build();
        }
    }

    @Transactional
    @Operation(summary = "Actualizar turno médico", description = "Este endpoint permitirá a los usuarios actualizar la información de un turno médico existente identificado por su ID.")
    @PUT
    @Path("/{id}")
    public Response actualizarTurno(@PathParam("id") Long id, TurnoDTO turnoDTO) {
        try {
            turnoService.actualizarTurno(id, turnoDTO);
            return Response.ok("Turno actualizado exitosamente").build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.severe("Error al actualizar el turno: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Hubo un error al intentar actualizar el turno: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/especialidad/{especialidad}")
    public Response obtenerTurnosPorEspecialidad(@PathParam("especialidad") String especialidad) {
        try {
            List<Turno> turnos = turnoService.obtenerTurnosPorEspecialidad(especialidad);
            return Response.ok(turnos).build();
        } catch (Exception e) {
            logger.severe("Error al obtener los turnos por especialidad: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Hubo un error al intentar obtener los turnos por especialidad: " + e.getMessage()).build();

        }
    }
}
