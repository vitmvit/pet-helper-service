package by.vitikova.discovery.controller;

import by.vitikova.discovery.AnimalTypeDto;
import by.vitikova.discovery.create.AnimalTypeCreateDto;
import by.vitikova.discovery.service.AnimalTypeService;
import by.vitikova.discovery.update.AnimalTypeUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/animalTypes")
public class AnimalTypeController {

    private AnimalTypeService animalTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<AnimalTypeDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(animalTypeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AnimalTypeDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(animalTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<AnimalTypeDto> create(@RequestBody AnimalTypeCreateDto animalTypeCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(animalTypeService.create(animalTypeCreateDto));
    }

    @PutMapping
    public ResponseEntity<AnimalTypeDto> update(@RequestBody AnimalTypeUpdateDto animalTypeUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(animalTypeService.update(animalTypeUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        animalTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
