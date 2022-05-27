package task.task.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.task.model.Car;
import task.task.repository.CarRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    //@Autowired
    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping(path = "allCars", produces = "application/json")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @PostMapping(path = "newCar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        carRepository.save(car);
        return ResponseEntity.ok().body(car);
    }

    @PutMapping(path = "updateCar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        Optional<Car> optionalCar = carRepository.findById(car.getId());
        if (optionalCar.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Car carToUpdate = optionalCar.get();
            carToUpdate.setName(car.getName());
            carRepository.save(carToUpdate);
            return ResponseEntity.ok().body(carToUpdate);
        }
    }

    @DeleteMapping(path = "deleteCar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Car> deleteCar(@RequestBody Car car) {
        Optional<Car> optionalCar = carRepository.findById(car.getId());
        if (optionalCar.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else{
            Car carToDelete = optionalCar.get();
            carRepository.deleteById(carToDelete.getId());
            return ResponseEntity.ok().body(carToDelete);
        }
    }
}
