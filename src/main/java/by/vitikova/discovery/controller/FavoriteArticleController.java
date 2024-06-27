package by.vitikova.discovery.controller;

import by.vitikova.discovery.FavoriteArticleDto;
import by.vitikova.discovery.create.FavoriteArticleCreateDto;
import by.vitikova.discovery.service.FavoriteArticleService;
import by.vitikova.discovery.update.FavoriteArticleUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/favoriteArticles")
public class FavoriteArticleController {

    private FavoriteArticleService favoriteArticleService;

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteArticleDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(favoriteArticleService.findById(id));
    }

    @GetMapping("/articleId/{id}")
    public ResponseEntity<FavoriteArticleDto> findByArticleId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(favoriteArticleService.findByArticleId(id));
    }

    @GetMapping("/article/{login}")
    public ResponseEntity<List<FavoriteArticleDto>> findByUserLogin(@PathVariable("login") String login) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(favoriteArticleService.findByUserLogin(login));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteArticleDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(favoriteArticleService.findAll());
    }

    @PostMapping
    public ResponseEntity<FavoriteArticleDto> create(@RequestBody FavoriteArticleCreateDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(favoriteArticleService.create(dto));
    }

    @PutMapping
    public ResponseEntity<FavoriteArticleDto> update(@RequestBody FavoriteArticleUpdateDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(favoriteArticleService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        favoriteArticleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
