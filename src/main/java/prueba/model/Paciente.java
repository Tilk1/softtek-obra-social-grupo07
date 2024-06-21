package prueba.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Paciente extends PanacheEntity {
    private String nombre;
    private String dni;
    private String numeroCelular;
    public String email;
    public String password;

    public Paciente() {
    }

    public Paciente(Long id, String nombre, String dni, String numeroCelular) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.numeroCelular = numeroCelular;
    }

}
