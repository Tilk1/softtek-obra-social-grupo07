package prueba.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Paciente extends PanacheEntity{
    private String nombre;
    private String dni;
    private String numeroCelular;
    private Boolean activo;

    public Paciente(){
        this.activo = true;
    }

}
