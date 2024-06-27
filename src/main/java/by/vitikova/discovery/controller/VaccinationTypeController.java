package by.vitikova.discovery.controller;

import by.vitikova.discovery.VaccinationTypeDto;
import by.vitikova.discovery.create.VaccinationTypeCreateDto;
import by.vitikova.discovery.service.VaccinationTypeService;
import by.vitikova.discovery.update.VaccinationTypeUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/vaccinationTypes")
public class VaccinationTypeController {

    private VaccinationTypeService vaccinationTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<VaccinationTypeDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vaccinationTypeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<VaccinationTypeDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vaccinationTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<VaccinationTypeDto> create(@RequestBody VaccinationTypeCreateDto animalTypeCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vaccinationTypeService.create(animalTypeCreateDto));
    }

    @PutMapping
    public ResponseEntity<VaccinationTypeDto> update(@RequestBody VaccinationTypeUpdateDto animalTypeUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vaccinationTypeService.update(animalTypeUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        vaccinationTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
