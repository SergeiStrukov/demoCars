package demoCar.models;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String email; // Email пользователя
    private String password; // Пароль пользователя
}