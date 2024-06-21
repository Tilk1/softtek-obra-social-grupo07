package prueba.service;

import java.util.HashMap;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import prueba.repository.PacienteRepository;
import prueba.DTO.UserLoginDTO;
import prueba.model.Paciente;
import prueba.security.TokenService;

@ApplicationScoped
public class AuthService {

    @Inject
    PacienteRepository pacienteRepository;

    @Inject
    TokenService service;

    public UserLoginDTO login(String mailString, String password) {
        if (mailString == null || password == null) {
            throw new IllegalArgumentException("Mail and password are required");
        }
        if (mailString.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Mail and password are required");
        }

        Paciente paciente = pacienteRepository.buscarPacientePorEmail(mailString);

        if (paciente == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!paciente.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        String token = service.generateUserToken(mailString, password);

        UserLoginDTO userData = new UserLoginDTO();
        userData.setToken(token);
        userData.setNombre(paciente.getNombre());
        userData.setEmail(paciente.getEmail());
        userData.setDni(paciente.getDni());
        userData.setNumeroCelular(paciente.getNumeroCelular());
    
        return userData;

    }

}
