package prueba.controller;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import prueba.DTO.UserLoginDTO;
import prueba.security.TokenService;
import prueba.service.AuthService;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("")
@ApplicationScoped
public class LoginController {

    @Inject
    TokenService service;

    @Inject
    private AuthService authService;

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)    
    public UserLoginDTO login(@QueryParam("login") String mailString, @QueryParam("password") String password) {
        try {
            return authService.login(mailString, password);

        } catch (Exception e) {
            throw new WebApplicationException("Login failed", Response.Status.UNAUTHORIZED);
        }
    }
}
