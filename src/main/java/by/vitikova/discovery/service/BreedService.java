package by.vitikova.discovery.service;

import by.vitikova.discovery.BreedDto;
import by.vitikova.discovery.create.BreedCreateDto;
import by.vitikova.discovery.update.BreedUpdateDto;

import java.util.List;

public interface BreedService {

    BreedDto findById(Long id);

    List<BreedDto> findByTypeId(Long id);

    List<BreedDto> findAll();

    BreedDto create(BreedCreateDto dto);

    BreedDto update(BreedUpdateDto dto);

    void delete(Long id);
}
