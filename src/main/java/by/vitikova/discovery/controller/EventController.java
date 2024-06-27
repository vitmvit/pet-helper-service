package by.vitikova.discovery.controller;

import by.vitikova.discovery.EventDto;
import by.vitikova.discovery.create.EventCreateDto;
import by.vitikova.discovery.service.EventService;
import by.vitikova.discovery.update.EventUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.findAll());
    }

    @GetMapping("dict/{id}")
    public ResponseEntity<List<EventDto>> findByDictionaryId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.findByDictionaryId(id));
    }

    @PostMapping
    public ResponseEntity<EventDto> create(@RequestBody EventCreateDto eventCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventService.create(eventCreateDto));
    }

    @PutMapping
    public ResponseEntity<EventDto> update(@RequestBody EventUpdateDto eventUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.update(eventUpdateDto));
    }

    @DeleteMapping("dict/{id}")
    public ResponseEntity<List<EventDto>> deleteBeforeDateByDictionaryId(@PathVariable("id") Long id) {
        eventService.deleteBeforeDateByDictionaryId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        eventService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
