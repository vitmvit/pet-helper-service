package by.vitikova.discovery.service;

import by.vitikova.discovery.FavoriteArticleDto;
import by.vitikova.discovery.create.FavoriteArticleCreateDto;
import by.vitikova.discovery.update.FavoriteArticleUpdateDto;

import java.util.List;

public interface FavoriteArticleService {

    FavoriteArticleDto findById(Long id);

    FavoriteArticleDto findByArticleId(Long id);

    List<FavoriteArticleDto> findByUserLogin(String name);

    List<FavoriteArticleDto> findAll();

    FavoriteArticleDto create(FavoriteArticleCreateDto dto);

    FavoriteArticleDto update(FavoriteArticleUpdateDto dto);

    void delete(Long id);
}
