package task.task.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.task.repository.BookingRepository;
import task.task.repository.CarRepository;
import task.task.repository.SlotRepository;

@RestController
@RequestMapping("/service")
public class ServiceController {
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;

    public ServiceController(CarRepository carRepository, BookingRepository bookingRepository, SlotRepository slotRepository) {
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
        this.slotRepository = slotRepository;
    }

    @DeleteMapping(path = "deleteAll", produces = "text/plain")
    public ResponseEntity<String> deleteAll() {
        bookingRepository.deleteAll();
        carRepository.deleteAll();
        slotRepository.deleteAll();
        if (carRepository.findAll().isEmpty() && slotRepository.findAll().isEmpty() && bookingRepository.findAll().isEmpty()) {
            return ResponseEntity.ok().body("Everything was deleted!");
        } else {
            return ResponseEntity.internalServerError().body("Error!");
        }
    }
}
