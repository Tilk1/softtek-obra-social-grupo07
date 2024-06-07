package prueba.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import prueba.model.Usuario;
import prueba.security.TokenService;
import prueba.service.RecetaService;
import prueba.service.AuthService;

@Path("")
@ApplicationScoped
public class LoginController {

    @Inject
    TokenService service;

    @Inject
    private AuthService authService;

    @GET
    @Path("/login")
    public String login(@QueryParam("login") String mailString, @QueryParam("password") String password) {
        try {
            return authService.login(mailString, password);
            
        } catch (Exception e) {
            throw new WebApplicationException("Login failed", Response.Status.UNAUTHORIZED);
        }
    }
}
