package by.vitikova.discovery.service;

import by.vitikova.discovery.StateDictionaryDto;
import by.vitikova.discovery.create.StateDictionaryCreateDto;
import by.vitikova.discovery.update.StateDictionaryUpdateDto;

import java.util.List;

public interface StateDictionaryService {

    StateDictionaryDto findById(Long id);

    List<StateDictionaryDto> findAll();

    List<StateDictionaryDto> findAllByRecordId(Long id);

    StateDictionaryDto create(StateDictionaryCreateDto dto);

    StateDictionaryDto update(StateDictionaryUpdateDto dto);

    void delete(Long id);
}