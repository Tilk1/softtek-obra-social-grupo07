package prueba.model;

import java.time.ZonedDateTime;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Turno extends PanacheEntity {
    public String nombrePaciente;
    public String dniPaciente;

    @ManyToOne(fetch = FetchType.LAZY)
    public Especialista especialista;

    public String motivoConsulta;
    public ZonedDateTime fechaHoraCita;
}
