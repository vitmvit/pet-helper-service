package by.vitikova.discovery.service;

import by.vitikova.discovery.ArticleDto;

import java.util.List;

public interface RecommenderService {

    List<ArticleDto> getArticle(String userLogin);
}