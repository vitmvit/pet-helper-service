package by.vitikova.discovery.controller;

import by.vitikova.discovery.RecordDto;
import by.vitikova.discovery.RecordUpdateDto;
import by.vitikova.discovery.create.RecordCreateDto;
import by.vitikova.discovery.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/records")
public class RecordController {

    private RecordService recordService;

    @GetMapping("/{id}")
    public ResponseEntity<RecordDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recordService.findById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RecordDto>> findByUserId(@PathVariable("id") String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recordService.findByUserId(id));
    }

    @GetMapping
    public ResponseEntity<List<RecordDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recordService.findAll());
    }

    @PostMapping
    public ResponseEntity<RecordDto> create(@RequestBody RecordCreateDto recordCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recordService.create(recordCreateDto));
    }

    @PutMapping("avatar/{id}/{uuid}")
    public ResponseEntity<RecordDto> updateAvatarUuid(@PathVariable("id") Long id, @PathVariable("uuid") String uuid) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recordService.updateAvatarUuid(id, uuid));
    }

    @PutMapping
    public ResponseEntity<RecordDto> update(@RequestBody RecordUpdateDto recordUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recordService.update(recordUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        recordService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
