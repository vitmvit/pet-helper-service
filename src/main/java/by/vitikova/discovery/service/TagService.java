package by.vitikova.discovery.service;

import by.vitikova.discovery.TagDto;
import by.vitikova.discovery.create.TagCreateDto;
import by.vitikova.discovery.update.TagUpdateDto;

import java.util.List;

public interface TagService {

    TagDto findById(Long id);

    List<TagDto> findByArticleId(Long id);

    TagDto findByName(String name);

    List<TagDto> findAll();

    TagDto create(TagCreateDto dto);

    List<TagDto> createAll(List<TagCreateDto> dtoList);

    TagDto update(TagUpdateDto dto);

    void deleteByArticleId(Long id);

    void delete(Long id);
}
