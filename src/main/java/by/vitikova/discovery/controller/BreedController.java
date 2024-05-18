package by.vitikova.discovery.controller;

import by.vitikova.discovery.BreedDto;
import by.vitikova.discovery.create.BreedCreateDto;
import by.vitikova.discovery.service.BreedService;
import by.vitikova.discovery.update.BreedUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/breeds")
public class BreedController {

    private BreedService breedService;

    @GetMapping("/{id}")
    public ResponseEntity<BreedDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(breedService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BreedDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(breedService.findAll());
    }

    @GetMapping("type/{id}")
    public ResponseEntity<List<BreedDto>> findByTypeId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(breedService.findByTypeId(id));
    }

    @PostMapping
    public ResponseEntity<BreedDto> create(@RequestBody BreedCreateDto breedCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(breedService.create(breedCreateDto));
    }

    @PutMapping
    public ResponseEntity<BreedDto> update(@RequestBody BreedUpdateDto breedUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(breedService.update(breedUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        breedService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
