package demoCar.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users") // Имя таблицы в базе данных
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Идентификатор пользователя

    @Column(unique = true, nullable = false)
    private String email; // Email пользователя

    @Column(nullable = false)
    private String password; // Пароль пользователя

    // Дополнительные поля, если нужно (например, имя, фамилия и т.д.)
}
