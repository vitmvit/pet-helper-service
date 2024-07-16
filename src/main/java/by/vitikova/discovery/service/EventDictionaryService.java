package by.vitikova.discovery.service;

import by.vitikova.discovery.EventDictionaryDto;
import by.vitikova.discovery.create.EventDictionaryCreateDto;
import by.vitikova.discovery.update.EventDictionaryUpdateDto;

import java.util.List;

public interface EventDictionaryService {

    EventDictionaryDto findById(Long id);

    List<EventDictionaryDto> findAll();

    List<EventDictionaryDto> findAllByRecordId(Long id);

    EventDictionaryDto create(EventDictionaryCreateDto dto);

    EventDictionaryDto update(EventDictionaryUpdateDto dto);

    void delete(Long id);
}