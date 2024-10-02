package demoCar.repositories;

import demoCar.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByEngineerName(String engineerName);
}
