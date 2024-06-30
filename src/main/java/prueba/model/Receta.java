package prueba.model;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Receta extends PanacheEntity {
    private String descripcion;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Turno turno;

    // Podria servir para la validez de la receta ?
    @CreationTimestamp
    private ZonedDateTime fechaCreacion;
}
