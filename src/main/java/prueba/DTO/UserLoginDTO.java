package prueba.DTO;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String nombre;
    private String dni;
    private String numeroCelular;
    private String email;
    private String token;
}
