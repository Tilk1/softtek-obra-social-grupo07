package prueba.model;

import jakarta.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.CreationTimestamp;
import java.time.ZonedDateTime;

@Entity
public class Turno extends PanacheEntity {
    public String nombrePaciente;
    public ZonedDateTime fechaHoraCita;
    public String idMedicoEspecialista;
    public String motivoConsulta;

    @CreationTimestamp
    public ZonedDateTime created;
}
