package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.VaccinationTypeDto;
import by.vitikova.discovery.converter.VaccinationTypeConverter;
import by.vitikova.discovery.create.VaccinationTypeCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.VaccinationTypeRepository;
import by.vitikova.discovery.service.VaccinationTypeService;
import by.vitikova.discovery.update.VaccinationTypeUpdateDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VaccinationTypeServiceImpl implements VaccinationTypeService {

    private static final Logger logger = LoggerFactory.getLogger(VaccinationTypeServiceImpl.class);
    private VaccinationTypeRepository vaccinationTypeRepository;
    private VaccinationTypeConverter vaccinationTypeConverter;


    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект VaccinationTypeDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "vaccinationType", key = "#id")
    @Override
    public VaccinationTypeDto findById(Long id) {
        logger.info("VaccinationTypeService: find vaccination type with id: " + id);
        return vaccinationTypeConverter.convert(vaccinationTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов VaccinationTypeDto, представляющих все записи.
     */
    @Override
    public List<VaccinationTypeDto> findAll() {
        logger.info("VaccinationTypeService: find all types");
        var vaccinationTypeList = vaccinationTypeRepository.findAll();
        return vaccinationTypeList.stream().map(vaccinationTypeConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект VaccinationTypeCreateDto, содержащий данные для создания записи.
     * @return Объект VaccinationTypeDto, представляющий созданную запись.
     */
    @CacheEvict(value = "vaccinationTypes", key = "#dto.name")
    @Transactional
    @Override
    public VaccinationTypeDto create(VaccinationTypeCreateDto dto) {
        logger.info("VaccinationTypeService: create vaccination type");
        if (!vaccinationTypeRepository.existsByName(dto.getName())) {
            var vaccinationType = vaccinationTypeConverter.convert(dto);
            return vaccinationTypeConverter.convert(vaccinationTypeRepository.save(vaccinationType));
        }
        throw new RuntimeException("Name is exist!");
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект VaccinationTypeUpdateDto, содержащий обновленные данные для записи.
     * @return Объект VaccinationTypeDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "vaccinationTypes", key = "#dto.id")
    @Transactional
    @Override
    public VaccinationTypeDto update(VaccinationTypeUpdateDto dto) {
        logger.info("VaccinationTypeService: update vaccination type with id: " + dto.getId());
        var vaccinationType = vaccinationTypeRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        vaccinationTypeConverter.merge(vaccinationType, dto);
        return vaccinationTypeConverter.convert(vaccinationTypeRepository.save(vaccinationType));
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "vaccinationTypes", allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("VaccinationTypeService: delete vaccination type with id: " + id);
        vaccinationTypeRepository.deleteById(id);
    }
}
