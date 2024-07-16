package by.vitikova.discovery.controller;

import by.vitikova.discovery.StateDictionaryDto;
import by.vitikova.discovery.create.StateDictionaryCreateDto;
import by.vitikova.discovery.service.StateDictionaryService;
import by.vitikova.discovery.update.StateDictionaryUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/dictionaries")
public class StateDictionaryController {

    private StateDictionaryService stateDictionaryService;

    @GetMapping("/{id}")
    public ResponseEntity<StateDictionaryDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stateDictionaryService.findById(id));
    }

    @GetMapping("/recordId/{id}")
    public ResponseEntity<List<StateDictionaryDto>> findAllByRecordId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stateDictionaryService.findAllByRecordId(id));
    }

    @GetMapping
    public ResponseEntity<List<StateDictionaryDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stateDictionaryService.findAll());
    }

    @PostMapping
    public ResponseEntity<StateDictionaryDto> create(@RequestBody StateDictionaryCreateDto stateDictionaryCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(stateDictionaryService.create(stateDictionaryCreateDto));
    }

    @PutMapping
    public ResponseEntity<StateDictionaryDto> update(@RequestBody StateDictionaryUpdateDto stateDictionaryUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stateDictionaryService.update(stateDictionaryUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        stateDictionaryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
