package demoCar.models;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String userName;
    private String email;
    private String password;
    private String role;
}
