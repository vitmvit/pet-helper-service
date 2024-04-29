package by.vitikova.discovery.service;

import by.vitikova.discovery.PedigreeDto;
import by.vitikova.discovery.create.PedigreeCreateDto;
import by.vitikova.discovery.update.PedigreeUpdateDto;

import java.util.List;

public interface PedigreeService {

    PedigreeDto findById(Long id);

    PedigreeDto findByRecordId(Long id);

    List<PedigreeDto> findAll();

    PedigreeDto create(PedigreeCreateDto dto);

    PedigreeDto update(PedigreeUpdateDto dto);

    void delete(Long id);
}
