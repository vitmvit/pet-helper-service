package by.vitikova.discovery.controller;

import by.vitikova.discovery.EventDictionaryDto;
import by.vitikova.discovery.create.EventDictionaryCreateDto;
import by.vitikova.discovery.service.EventDictionaryService;
import by.vitikova.discovery.update.EventDictionaryUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/eventDictionaries")
public class EventDictionaryController {

    private EventDictionaryService eventDictionaryService;

    @GetMapping("/{id}")
    public ResponseEntity<EventDictionaryDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDictionaryService.findById(id));
    }

    @GetMapping("/recordId/{id}")
    public ResponseEntity<List<EventDictionaryDto>> findAllByRecordId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDictionaryService.findAllByRecordId(id));
    }

    @GetMapping
    public ResponseEntity<List<EventDictionaryDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDictionaryService.findAll());
    }

    @PostMapping
    public ResponseEntity<EventDictionaryDto> create(@RequestBody EventDictionaryCreateDto eventDictionaryCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventDictionaryService.create(eventDictionaryCreateDto));
    }

    @PutMapping
    public ResponseEntity<EventDictionaryDto> update(@RequestBody EventDictionaryUpdateDto eventDictionaryUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDictionaryService.update(eventDictionaryUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        eventDictionaryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
