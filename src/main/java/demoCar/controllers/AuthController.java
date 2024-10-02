package demoCar.controllers;

import demoCar.models.LoginRequestDto;
import demoCar.models.User;
import demoCar.models.UserRegisterDto;
import demoCar.services.UserService; // Импортируем сервис
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import demoCar.security.JwtTokenUtil;
@RestController
public class AuthController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService; // Внедрение UserService

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());
        // Проверяем email и password
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            // Генерация токена
            String token = jwtTokenUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(token); // Возвращаем токен
        } else {
            // Если аутентификация не удалась
            return ResponseEntity.status(401).body("Unauthorized: Invalid email or password");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        userService.save(user); // Сохраняем пользователя
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}
