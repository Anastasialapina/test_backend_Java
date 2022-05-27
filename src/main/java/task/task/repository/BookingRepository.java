package task.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.task.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
