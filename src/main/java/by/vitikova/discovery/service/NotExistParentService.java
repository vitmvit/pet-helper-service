package by.vitikova.discovery.service;

import by.vitikova.discovery.NotExistParentDto;
import by.vitikova.discovery.create.NotExistParentCreateDto;
import by.vitikova.discovery.update.NotExistParentUpdateDto;

import java.util.List;

public interface NotExistParentService {

    NotExistParentDto findById(Long id);

    List<NotExistParentDto> findAll();

    NotExistParentDto create(NotExistParentCreateDto dto);

    NotExistParentDto update(NotExistParentUpdateDto dto);

    void delete(Long id);
}