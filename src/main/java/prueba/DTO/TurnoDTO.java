package prueba.DTO;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TurnoDTO {
    public String nombrePaciente;
    public String DNIPaciente;
    public Long idMedicoEspecialista;
    public String motivoConsulta;
    public ZonedDateTime fechaHoraCita;
}
