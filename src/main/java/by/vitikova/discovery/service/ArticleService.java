package by.vitikova.discovery.service;

import by.vitikova.discovery.ArticleDto;
import by.vitikova.discovery.EventDto;
import by.vitikova.discovery.constant.ArticleStatus;
import by.vitikova.discovery.create.ArticleCreateDto;
import by.vitikova.discovery.create.EventCreateDto;
import by.vitikova.discovery.update.ArticleUpdateDto;
import by.vitikova.discovery.update.EventUpdateDto;

import java.util.List;

public interface ArticleService {

    ArticleDto findById(Long id);

    List<ArticleDto> findByEditorName(String name);

    List<ArticleDto> findAll();

    List<ArticleDto> findAllByStatus(ArticleStatus status);

    ArticleDto create(ArticleCreateDto dto);

    ArticleDto update(ArticleUpdateDto dto);

    void delete(Long id);
}
