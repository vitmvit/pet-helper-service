package by.vitikova.discovery.controller;

import by.vitikova.discovery.ArticleDto;
import by.vitikova.discovery.create.ArticleCreateDto;
import by.vitikova.discovery.service.ArticleService;
import by.vitikova.discovery.update.ArticleUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.findById(id));
    }

    @GetMapping("/editor/{name}")
    public ResponseEntity<List<ArticleDto>> findByEditorName(@PathVariable("name") String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.findByEditorName(name));
    }

    @GetMapping
    public ResponseEntity<List<ArticleDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.findAll());
    }

    @PostMapping
    public ResponseEntity<ArticleDto> create(@RequestBody ArticleCreateDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.create(dto));
    }

    @PutMapping
    public ResponseEntity<ArticleDto> update(@RequestBody ArticleUpdateDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        articleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
