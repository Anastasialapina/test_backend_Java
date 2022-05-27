package task.task.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.task.model.Slot;
import task.task.repository.SlotRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/slot")
public class SlotController {

    private final SlotRepository slotRepository;

    public SlotController(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @GetMapping(path = "allSlots", produces = "application/json")
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    @PostMapping(path = "newSlot", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Slot> createSlot(@RequestBody Slot slot) {
        Slot newSlot = slotRepository.save(slot);
        return ResponseEntity.ok().body(newSlot);
    }

    @DeleteMapping(path = "deleteSlot", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Slot> deleteSlot(@RequestBody Slot slot) {
        Optional<Slot> optionalSlot = slotRepository.findById(slot.getId());
        if (optionalSlot.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            slotRepository.deleteById(optionalSlot.get().getId());
            return ResponseEntity.ok().body(slot);
        }
    }
}
