package by.vitikova.discovery.controller;

import by.vitikova.discovery.StateTemplateDto;
import by.vitikova.discovery.create.StateTemplateCreateDto;
import by.vitikova.discovery.service.StateTemplateService;
import by.vitikova.discovery.update.StateTemplateUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/stateTemplates")
public class StateTemplateController {

    private StateTemplateService stateTemplateService;

    @GetMapping("/{id}")
    public ResponseEntity<StateTemplateDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stateTemplateService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<StateTemplateDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stateTemplateService.findAll());
    }

    @PostMapping
    public ResponseEntity<StateTemplateDto> create(@RequestBody StateTemplateCreateDto stateCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(stateTemplateService.create(stateCreateDto));
    }

    @PutMapping
    public ResponseEntity<StateTemplateDto> update(@RequestBody StateTemplateUpdateDto stateUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stateTemplateService.update(stateUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        stateTemplateService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
