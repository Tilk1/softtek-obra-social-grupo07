package prueba.controller;

import java.util.logging.Logger;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import prueba.service.PacienteService;
import prueba.DTO.PacienteDTO;
import prueba.model.Paciente;

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
            return Response.status(400).entity("Hubo un error al intentar obtener un Paciente: " + e.getMessage()).build();
        }
    }    

    /**
     * Anda pero solo pasandole el objeto entero, estaba buscando la manera de solo pasarle una 
     * parte del objeto
     */
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


    @DELETE
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response desactivarPaciente(@PathParam("id") Long id){
        try{
            pacienteService.desactivarPaciente(id);
            return Response.ok("Se elimino correctamente el Paciente").build();
        }catch (Exception e){
            logger.severe("Error al eliminar un Pacinete: " + e.getMessage());
            return Response.status(400).entity("Hubo un error al eliminar un Paciente: " + e.getMessage()).build();
        }
    }

}