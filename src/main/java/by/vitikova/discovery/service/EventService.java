package by.vitikova.discovery.service;

import by.vitikova.discovery.EventDto;
import by.vitikova.discovery.create.EventCreateDto;
import by.vitikova.discovery.update.EventUpdateDto;

import java.util.List;

public interface EventService {

    EventDto findById(Long id);

    List<EventDto> findByDictionaryId(Long id);

    void deleteBeforeDateByDictionaryId(Long id);

    List<EventDto> findAll();

    EventDto create(EventCreateDto dto);

    EventDto update(EventUpdateDto dto);

    void delete(Long id);
}