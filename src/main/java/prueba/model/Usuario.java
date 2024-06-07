package prueba.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Usuario extends PanacheEntity {
    public String nombre;
    public String dni;
    public String email;
    public String password;

    public Usuario() {
    }
}
