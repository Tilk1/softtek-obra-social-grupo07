package prueba.controller;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import prueba.DTO.EspecialistaDTO;
import prueba.model.Especialista;
import prueba.service.EspecialistaService;

@Path("/especialistas")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EspecialistaController {

    @Inject
    EspecialistaService especialistaService;

    @GET
    @Path("/{id}")
    public Response obtenerEspecialistaPorId(@PathParam("id") Long id){
        try{
            Especialista especialista = especialistaService.obtenerEspecialistaPorId(id);
            return Response.ok(especialista).build();
        } catch(Exception e){
            return Response.serverError().build();
        }
    }

    @GET
    public List<Especialista> obtenerEspecialistas() {
        return especialistaService.obtenerEspecialistas();
    }

    @POST 
    public Response crearEspecialista(EspecialistaDTO especialista){
        try{
            especialistaService.crearEspecialista(especialista);
            return Response.ok("Se creo con exito!").build();
        }catch(Exception e){
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizarEspecialista(@PathParam("id") Long id, EspecialistaDTO especialista){
        try{
            especialistaService.actualizarEspecialista(id, especialista);
            return Response.ok("Se actualizo exitosamente los datos").build();
        }catch(Exception e){
            return Response.serverError().build();
        }
    }

}