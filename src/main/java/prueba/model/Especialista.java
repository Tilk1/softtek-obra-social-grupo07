package prueba.model;

import jakarta.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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