package prueba.controller;

import java.util.logging.Logger;

import org.eclipse.microprofile.openapi.annotations.Operation;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import prueba.service.RecetaService;
import prueba.DTO.RecetaDTO;
import prueba.model.Receta;
import prueba.security.Roles;

@Path("/recetas")
@ApplicationScoped
public class RecetaController {

    @Inject
    private RecetaService recetaService;

    private static final Logger logger = Logger.getLogger(RecetaController.class.getName());

    @GET
    public Response obtenerRecetas() {
        try {
            // Hay que ver si quieren el archivo o solo el objeto
            List<Receta> respuesta = recetaService.obtenerRecetas();
            if (respuesta == null) {
                return Response.status(404, "No se encontraron recetas").build();
            }
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            logger.severe("Hubo un error al intentar obtener una receta: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al intentar obtener las recetas").build();
        }
    }

    @Operation(summary = "Descargar receta médica", description = "Este endpoint permitirá a los pacientes autorizados descargar su receta médica proporcionando el ID del turno asociado a la receta. Se verificará la autenticación del usuario y la validez de la receta antes de permitir la descarga")
    @GET
    @Path("/{id}")
    @RolesAllowed({ Roles.USER })
    public Response obtenerRecetaPorIdTurno(@PathParam("id") Long id) {
        try {
            // Hay que ver si quieren el archivo o solo el objeto
            Receta respuesta = recetaService.obtenerRecetaPorIdTurno(id);
            if (respuesta == null) {
                return Response.status(404, "No se encontro la Receta").build();
            }
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            logger.severe("Hubo un error al intentar obtener una receta: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al intentar obtener una receta: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearReceta(RecetaDTO receta) {
        try {
            recetaService.crearReceta(receta);
            return Response.ok("Se creo la receta exitosamente").build();
        } catch (Exception e) {
            logger.severe("Error al guardar la receta: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al guardar la receta: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/id={id}")
    @RolesAllowed({ Roles.USER })
    public Response obtenerRecetaPorId(@PathParam("id") Long id) {
        try {
            // Hay que ver si quieren el archivo o solo el objeto
            Receta respuesta = recetaService.obtenerRecetaPorId(id);
            if (respuesta == null) {
                return Response.status(404, "No se encontro la Receta").build();
            }
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            logger.severe("Hubo un error al intentar obtener una receta: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al intentar obtener una receta: " + e.getMessage())
                    .build();
        }
    }
}