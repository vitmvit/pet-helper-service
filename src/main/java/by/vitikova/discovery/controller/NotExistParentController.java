package by.vitikova.discovery.controller;

import by.vitikova.discovery.NotExistParentDto;
import by.vitikova.discovery.create.NotExistParentCreateDto;
import by.vitikova.discovery.service.NotExistParentService;
import by.vitikova.discovery.update.NotExistParentUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/parents")
public class NotExistParentController {

    private NotExistParentService parentService;

    @GetMapping("/{id}")
    public ResponseEntity<NotExistParentDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<NotExistParentDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parentService.findAll());
    }

    @PostMapping
    public ResponseEntity<NotExistParentDto> create(@RequestBody NotExistParentCreateDto notExistParentCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(parentService.create(notExistParentCreateDto));
    }

    @PutMapping
    public ResponseEntity<NotExistParentDto> update(@RequestBody NotExistParentUpdateDto notExistParentUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parentService.update(notExistParentUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        parentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}