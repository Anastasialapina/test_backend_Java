package task.task.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.task.model.Booking;
import task.task.repository.BookingRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping(path = "allBooking")
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @PostMapping(path = "createBooking", produces = "application/json")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking newBooking = new Booking();
        try {
           newBooking = bookingRepository.save(booking);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(newBooking);
    }

    @PutMapping(path = "updateBooking", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking) {
        Optional<Booking> optionalBooking = bookingRepository.findById(booking.getId());
        if (optionalBooking.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Booking updateBooking = optionalBooking.get();
            if (booking.getCar() != null)
                updateBooking.setCar(booking.getCar());
            if (booking.getBookingTime() != null)
                updateBooking.setBookingTime(booking.getBookingTime());
            if (booking.getId() != 0)
                updateBooking.setId(booking.getId());
            if (booking.getSlot() != null)
                updateBooking.setSlot(booking.getSlot());
            if (booking.getPrice() != 0)
                updateBooking.setPrice(booking.getPrice());
            bookingRepository.save(updateBooking);
            return ResponseEntity.ok().body(updateBooking);
        }
    }

    @DeleteMapping(path = "deleteBooking", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Booking> deleteBoking(@RequestBody Booking booking) {
        Optional<Booking> optionalBooking = bookingRepository.findById(booking.getId());
        if (optionalBooking.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            bookingRepository.deleteById(optionalBooking.get().getId());
            return ResponseEntity.ok().body(booking);
        }
    }
}
