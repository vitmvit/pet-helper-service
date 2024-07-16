package by.vitikova.discovery.controller;

import by.vitikova.discovery.PedigreeDto;
import by.vitikova.discovery.create.PedigreeCreateDto;
import by.vitikova.discovery.service.PedigreeService;
import by.vitikova.discovery.update.PedigreeUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/pedigree")
public class PedigreeController {

    private PedigreeService recordService;

    @GetMapping("/{id}")
    public ResponseEntity<PedigreeDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recordService.findById(id));
    }

    @GetMapping("/record/{id}")
    public ResponseEntity<PedigreeDto> findByRecordId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recordService.findByRecordId(id));
    }

    @GetMapping
    public ResponseEntity<List<PedigreeDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recordService.findAll());
    }

    @PostMapping
    public ResponseEntity<PedigreeDto> create(@RequestBody PedigreeCreateDto pedigreeCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recordService.create(pedigreeCreateDto));
    }

    @PutMapping
    public ResponseEntity<PedigreeDto> update(@RequestBody PedigreeUpdateDto pedigreeUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recordService.update(pedigreeUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        recordService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}