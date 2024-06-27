package by.vitikova.discovery.service;

import by.vitikova.discovery.VaccinationTypeDto;
import by.vitikova.discovery.create.VaccinationTypeCreateDto;
import by.vitikova.discovery.update.VaccinationTypeUpdateDto;

import java.util.List;

public interface VaccinationTypeService {

    VaccinationTypeDto findById(Long id);

    List<VaccinationTypeDto> findAll();

    VaccinationTypeDto create(VaccinationTypeCreateDto dto);

    VaccinationTypeDto update(VaccinationTypeUpdateDto dto);

    void delete(Long id);
}
