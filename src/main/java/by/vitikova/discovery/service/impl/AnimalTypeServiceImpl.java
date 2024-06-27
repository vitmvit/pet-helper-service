package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.AnimalTypeDto;
import by.vitikova.discovery.converter.AnimalTypeConverter;
import by.vitikova.discovery.create.AnimalTypeCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.AnimalTypeRepository;
import by.vitikova.discovery.service.AnimalTypeService;
import by.vitikova.discovery.update.AnimalTypeUpdateDto;
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
public class AnimalTypeServiceImpl implements AnimalTypeService {

    private static final Logger logger = LoggerFactory.getLogger(AnimalTypeServiceImpl.class);
    private AnimalTypeRepository animalTypeRepository;
    private AnimalTypeConverter animalTypeConverter;


    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект AnimalTypeDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "animalType", key = "#id")
    @Override
    public AnimalTypeDto findById(Long id) {
        logger.info("AnimalTypeService: find animal type with id: " + id);
        return animalTypeConverter.convert(animalTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public boolean existByName(String name) {
        logger.info("AnimalTypeService: exist animal type with name: " + name);
        return animalTypeRepository.existsByName(name);
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов AnimalTypeDto, представляющих все записи.
     */
    @Override
    public List<AnimalTypeDto> findAll() {
        logger.info("AnimalTypeService: find all types");
        var animalTypeList = animalTypeRepository.findAll();
        return animalTypeList.stream().map(animalTypeConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект AnimalTypeCreateDto, содержащий данные для создания записи.
     * @return Объект AnimalTypeDto, представляющий созданную запись.
     */
    @CacheEvict(value = "animalTypes", key = "#dto.name")
    @Transactional
    @Override
    public AnimalTypeDto create(AnimalTypeCreateDto dto) {
        logger.info("AnimalTypeService: create animal type");
        if (!animalTypeRepository.existsByName(dto.getName())) {
            var animalType = animalTypeConverter.convert(dto);
            return animalTypeConverter.convert(animalTypeRepository.save(animalType));
        }
        throw new RuntimeException("Name is exist!");
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект AnimalTypeUpdateDto, содержащий обновленные данные для записи.
     * @return Объект AnimalTypeDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "animalTypes", key = "#dto.id")
    @Transactional
    @Override
    public AnimalTypeDto update(AnimalTypeUpdateDto dto) {
        logger.info("AnimalTypeService: update animal type with id: " + dto.getId());
        var animalType = animalTypeRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        animalTypeConverter.merge(animalType, dto);
        return animalTypeConverter.convert(animalTypeRepository.save(animalType));
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "animalTypes", allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("AnimalTypeService: delete animal type with id: " + id);
        animalTypeRepository.deleteById(id);
    }
}
