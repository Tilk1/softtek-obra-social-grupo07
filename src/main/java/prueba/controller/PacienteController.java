package prueba.controller;

import java.util.List;
import java.util.logging.Logger;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import prueba.DTO.PacienteDTO;
import prueba.model.Paciente;
import prueba.service.PacienteService;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;

import prueba.model.Turno;
import prueba.security.Roles;

@Path("/pacientes")
@ApplicationScoped
public class PacienteController {

    @Inject
    private PacienteService pacienteService;

    private static final Logger logger = Logger.getLogger(PacienteController.class.getName());

    @GET
    public Response obtenerpacientes() {
        try {
            List<Paciente> respuesta = pacienteService.obtenerPacientes();
            if (respuesta == null) {
                return Response.status(404, "No se encontraron pacientes").build();
            }
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            logger.severe("Hubo un error al intentar obtener una Paciente: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al intentar obtener las pacientes").build();
        }
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearPaciente(PacienteDTO Paciente) {
        try {
            pacienteService.crearPaciente(Paciente);
            return Response.ok("Se creo la Paciente exitosamente").build();
        } catch (Exception e) {
            logger.severe("Error al guardar la Paciente: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al guardar la Paciente: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response obtenerPacientePorId(@PathParam("id") Long id) {
        try {
            Paciente respuesta = pacienteService.obtenerPacientePorId(id);
            if (respuesta == null) {
                return Response.status(404, "No se encontro el Paciente").build();
            }
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            logger.severe("Hubo un error al intentar obtener un Paciente: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al intentar obtener un Paciente: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarPaciente(@PathParam("id") Long id, PacienteDTO paciente) {
        try {
            pacienteService.actualizarPaciente(id, paciente);
            return Response.ok("Se actualizo el Paciente exitosamente").build();
        } catch (Exception e) {
            logger.severe("Error al guardar la Paciente: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al guardar el Paciente: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/mis/turnos")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ Roles.USER })
    public Response misTurnos(@Context HttpHeaders headers) {
        try {
            String authHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
            System.out.println("authHeader: " + authHeader);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return Response.status(401, "No se pudo obtener el token de autenticación").build();
            }

            String token = authHeader.substring(7); // Extrae el token
            List<Turno> respuesta = pacienteService.misTurnos(token);

            if (respuesta == null || respuesta.isEmpty()) {
                return Response.status(204, "No se encontraron turnos").build();
            }
            return Response.ok(respuesta).build();
        } catch (IllegalStateException e) {
            logger.severe("Token processing issue: " + e.getMessage());
            return Response.status(401, "No se pudo procesar el token de autenticación").build();
        } catch (Exception e) {
            logger.severe("Hubo un error al intentar obtener los turnos: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al intentar obtener los turnos").build();
        }
    }

}