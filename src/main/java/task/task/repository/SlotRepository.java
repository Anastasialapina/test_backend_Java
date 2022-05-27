package task.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.task.model.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {
}
