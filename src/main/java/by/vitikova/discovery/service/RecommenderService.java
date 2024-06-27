package by.vitikova.discovery.service;

import by.vitikova.discovery.ArticleDto;

import java.util.List;
import java.util.Set;

public interface RecommenderService {

    List<ArticleDto> getArticle(String userLogin);
}
