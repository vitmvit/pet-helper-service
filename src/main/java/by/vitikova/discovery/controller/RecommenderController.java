package by.vitikova.discovery.controller;

import by.vitikova.discovery.ArticleDto;
import by.vitikova.discovery.service.RecommenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/recommender")
public class RecommenderController {

    private RecommenderService recommenderService;

    @GetMapping("/{login}")
    public ResponseEntity<List<ArticleDto>> findById(@PathVariable("login") String login) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recommenderService.getArticle(login));
    }
}
