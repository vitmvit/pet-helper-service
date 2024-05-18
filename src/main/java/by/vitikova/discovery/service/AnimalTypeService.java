package by.vitikova.discovery.service;

import by.vitikova.discovery.AnimalTypeDto;
import by.vitikova.discovery.create.AnimalTypeCreateDto;
import by.vitikova.discovery.update.AnimalTypeUpdateDto;

import java.util.List;

public interface AnimalTypeService {

    AnimalTypeDto findById(Long id);

    boolean existByName(String name);

    List<AnimalTypeDto> findAll();

    AnimalTypeDto create(AnimalTypeCreateDto dto);

    AnimalTypeDto update(AnimalTypeUpdateDto dto);

    void delete(Long id);
}
