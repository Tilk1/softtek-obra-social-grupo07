package prueba.controller;

import java.util.logging.Logger;

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

import prueba.model.Receta;

@Path("/recetas")
@ApplicationScoped
public class RecetaController {

    @Inject
    private RecetaService recetaService;

    private static final Logger logger = Logger.getLogger(RecetaController.class.getName());

    @GET
    @Path("/{id}")
    public Response getRecetaById(@PathParam("id") Long id) {
        try {
            // Hay que ver si quieren el archivo o solo el objeto
            Receta respuesta = recetaService.getByTurnoId(id);
            if (respuesta == null) {
                return Response.status(404, "No se encontro la Receta").build();
            }
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            logger.severe("Hubo un error al intentar obtener una receta: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al intentar obtener una receta: " + e.getMessage()).build();
        }
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveReceta(Receta receta) {
        try {
            recetaService.save(receta);
            return Response.ok("Se creo la receta exitosamente").build();
        } catch (Exception e) {
            logger.severe("Error al guardar la receta: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al guardar la receta: " + e.getMessage()).build();
        }
    }
}