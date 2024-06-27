package by.vitikova.discovery.controller;

import by.vitikova.discovery.TagDto;
import by.vitikova.discovery.create.TagCreateDto;
import by.vitikova.discovery.service.TagService;
import by.vitikova.discovery.update.TagUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {

    private TagService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemService.findById(id));
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<List<TagDto>> findByArticleId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemService.findByArticleId(id));
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemService.findAll());
    }

    @PostMapping("/all")
    public ResponseEntity<List<TagDto>> createAll(@RequestBody List<TagCreateDto> dtoList) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemService.createAll(dtoList));
    }

    @PostMapping
    public ResponseEntity<TagDto> create(@RequestBody TagCreateDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemService.create(dto));
    }

    @PutMapping
    public ResponseEntity<TagDto> update(@RequestBody TagUpdateDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemService.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteByArticleId(@PathVariable("id") Long id) {
        itemService.deleteByArticleId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        itemService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
