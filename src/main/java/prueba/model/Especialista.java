package prueba.model;

import jakarta.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Especialista extends PanacheEntity {
    private String nombre;
    private String especialidad;
    private String horariosConsulta;
    private String ubicacion;

    @CreationTimestamp
    private LocalDate fechaCreacion;

}