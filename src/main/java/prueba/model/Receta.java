package prueba.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Receta extends PanacheEntity {
    private String descripcion;

    @OneToOne(fetch = FetchType.LAZY)
    private Turno turno;

    // Podria servir para la validez de la receta ?
    @CreationTimestamp
    private LocalDate fechaCreacion;
}
