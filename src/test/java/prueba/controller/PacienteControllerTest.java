package prueba.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import prueba.model.Paciente;
import prueba.service.PacienteService;

import jakarta.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacienteControllerTest {

    @Mock
    private PacienteService pacienteService;

    @InjectMocks
    private PacienteController pacienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerpacientes() {

        Paciente paciente = new Paciente(1L, "Paco", "12345678", "099123456");
        List<Paciente> pacientes = Collections.singletonList(paciente);

        when(pacienteService.obtenerPacientes()).thenReturn(pacientes);

        Response response = pacienteController.obtenerpacientes();
        assertEquals(200, response.getStatus());
        assertEquals(pacientes, response.getEntity());
        verify(pacienteService, times(1)).obtenerPacientes();
    }

    @Test
    void testObtenerpacientesNotFound() {

        when(pacienteService.obtenerPacientes()).thenReturn(null);

        Response response = pacienteController.obtenerpacientes();
        assertEquals(404, response.getStatus());
        verify(pacienteService, times(1)).obtenerPacientes();
    }

    @Test
    void testObtenerpacientesException() {

        when(pacienteService.obtenerPacientes()).thenThrow(new RuntimeException("Error"));

        Response response = pacienteController.obtenerpacientes();
        assertEquals(400, response.getStatus());
        verify(pacienteService, times(1)).obtenerPacientes();
    }
}
