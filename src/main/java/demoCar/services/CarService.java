package demoCar.services;

import demoCar.models.Car;
import demoCar.repositories.CarRepository;
import demoCar.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car carDetails) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
        car.setBrand(carDetails.getBrand());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setCityOfManufacture(carDetails.getCityOfManufacture());
        car.setEngineerName(carDetails.getEngineerName());
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
        carRepository.delete(car);
    }

    public List<Car> getCarsByEngineerName(String engineerName) {
        return carRepository.findByEngineerName(engineerName);
    }
}
