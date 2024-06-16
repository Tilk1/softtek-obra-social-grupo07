package prueba.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import prueba.DTO.EspecialistaDTO;
import prueba.service.EspecialistaService;

@ExtendWith(MockitoExtension.class)
public class EspecialistaControllerTest {

    @Mock
    private EspecialistaService especialistaService;

    @InjectMocks
    private EspecialistaController especialistaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearEspecialista() {
        EspecialistaDTO especialistaDTO = new EspecialistaDTO();
        especialistaDTO.setNombre("Dr. House");
        especialistaDTO.setEspecialidad("Diagnóstico");

        Response response = especialistaController.crearEspecialista(especialistaDTO);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("{¡El especialista se creó con éxito!}", response.getEntity());

        verify(especialistaService, times(1)).crearEspecialista(especialistaDTO);
    }
}
