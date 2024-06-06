package prueba.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import prueba.DTO.TurnoDTO;
import prueba.service.TurnoService;

@Path("/turnos")
@ApplicationScoped
public class TurnoController {

    @Inject
    TurnoService turnoService;

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearTurno(TurnoDTO turnoDTO) {
        turnoService.guardarTurno(turnoDTO);
        return Response.ok("Turno creado exitosamente").build();
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
}
