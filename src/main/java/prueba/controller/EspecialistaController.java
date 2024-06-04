package prueba.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import prueba.repository.TurnoRepository;

@Path("/turnos")
@ApplicationScoped
public class TurnoController {

    @Inject
    TurnoRepository turnoRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearTurno(Turno turno) {
        // Verificar y procesar los datos del turno
        // Guardar el turno en la base de datos utilizando el repositorio
        turnoRepository.guardarTurno(turno);
        
        return Response.ok("Turno creado exitosamente").build();
    }
}