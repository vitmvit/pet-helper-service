package by.vitikova.discovery.service;

import by.vitikova.discovery.StateDto;
import by.vitikova.discovery.create.StateCreateDto;
import by.vitikova.discovery.update.StateUpdateDto;

import java.util.List;

public interface StateService {

    StateDto findById(Long id);

    List<StateDto> findByDictionaryId(Long id);

    List<StateDto> findAll();

    StateDto create(StateCreateDto dto);

    StateDto update(StateUpdateDto dto);

    void delete(Long id);
}
