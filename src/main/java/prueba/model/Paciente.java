package prueba.model;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Turno> turnos;

    public Paciente() {
    }

    public Paciente(Long id, String nombre, String dni, String numeroCelular) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.numeroCelular = numeroCelular;
    }

}
