package demoCar.repositories;

import demoCar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // Метод для поиска пользователя по email
}
