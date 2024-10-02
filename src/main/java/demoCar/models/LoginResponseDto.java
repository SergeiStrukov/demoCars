package demoCar.models;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String email;
    private String token;
}
