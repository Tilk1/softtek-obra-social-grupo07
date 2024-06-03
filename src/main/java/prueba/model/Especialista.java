package prueba.model;

import jakarta.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.CreationTimestamp;
import java.time.ZonedDateTime;

@Entity
public class Especialista extends PanacheEntity {
    public String name;
    public String especialidad;
    public String horariosConsulta;
    public String ubicacion;

    @CreationTimestamp
    public ZonedDateTime created;

}