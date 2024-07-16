package by.vitikova.discovery.controller;

import by.vitikova.discovery.StateDto;
import by.vitikova.discovery.create.StateCreateDto;
import by.vitikova.discovery.service.StateService;
import by.vitikova.discovery.update.StateUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/states")
public class StateController {

    private StateService stateService;

    @GetMapping("/{id}")
    public ResponseEntity<StateDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stateService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<StateDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stateService.findAll());
    }

    @GetMapping("dict/{id}")
    public ResponseEntity<List<StateDto>> findByDictionaryId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stateService.findByDictionaryId(id));
    }

    @PostMapping
    public ResponseEntity<StateDto> create(@RequestBody StateCreateDto stateCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(stateService.create(stateCreateDto));
    }

    @PutMapping
    public ResponseEntity<StateDto> update(@RequestBody StateUpdateDto stateUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stateService.update(stateUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        stateService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
