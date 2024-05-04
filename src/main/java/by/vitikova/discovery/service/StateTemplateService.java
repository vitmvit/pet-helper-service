package by.vitikova.discovery.service;

import by.vitikova.discovery.StateTemplateDto;
import by.vitikova.discovery.create.StateTemplateCreateDto;
import by.vitikova.discovery.update.StateTemplateUpdateDto;

import java.util.List;

public interface StateTemplateService {

    StateTemplateDto findById(Long id);

    List<StateTemplateDto> findAll();

    StateTemplateDto create(StateTemplateCreateDto dto);

    StateTemplateDto update(StateTemplateUpdateDto dto);

    void delete(Long id);
}
