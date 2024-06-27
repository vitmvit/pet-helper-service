package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.BreedDto;
import by.vitikova.discovery.converter.BreedConverter;
import by.vitikova.discovery.create.BreedCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.AnimalTypeRepository;
import by.vitikova.discovery.repository.BreedRepository;
import by.vitikova.discovery.service.BreedService;
import by.vitikova.discovery.update.BreedUpdateDto;
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
public class BreedServiceImpl implements BreedService {

    private static final Logger logger = LoggerFactory.getLogger(BreedServiceImpl.class);
    private BreedRepository breedRepository;
    private AnimalTypeRepository animalTypeRepository;
    private BreedConverter breedConverter;

    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект BreedDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "breed", key = "#id")
    @Override
    public BreedDto findById(Long id) {
        logger.info("BreedService: find breed with id: " + id);
        return breedConverter.convert(breedRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Метод для получения списка объектов BreedDto, соответствующих словарю с заданным идентификатором.
     *
     * @param id идентификатор типа животного, для которого необходимо получить список состояний
     * @return список объектов BreedDto, соответствующих заданному словарю
     */
    @Override
    public List<BreedDto> findByTypeId(Long id) {
        logger.info("BreedService: find breeds with animal type id: " + id);
        var breedList = breedRepository.findBreedByType_Id(id);
        return breedList.stream().map(breedConverter::convert).collect(Collectors.toList());
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов BreedDto, представляющих все записи.
     */
    @Override
    public List<BreedDto> findAll() {
        logger.info("BreedService: find all breeds");
        var stateList = breedRepository.findAll();
        return stateList.stream().map(breedConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект BreedCreateDto, содержащий данные для создания записи.
     * @return Объект BreedDto, представляющий созданную запись.
     */
    @CacheEvict(value = "breeds", key = "#dto.typeId")
    @Transactional
    @Override
    public BreedDto create(BreedCreateDto dto) {
        logger.info("BreedService: create breed");
        var dict = animalTypeRepository.findById(dto.getTypeId()).orElseThrow(EntityNotFoundException::new);
        var state = breedConverter.convert(dto);
        state.setType(dict);
        return breedConverter.convert(breedRepository.save(state));
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект BreedUpdateDto, содержащий обновленные данные для записи.
     * @return Объект BreedDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "breeds", key = "#dto.id")
    @Transactional
    @Override
    public BreedDto update(BreedUpdateDto dto) {
        logger.info("BreedService: update breed with id: " + dto.getId());
        var record = breedRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        breedConverter.merge(record, dto);
        return breedConverter.convert(breedRepository.save(record));
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "breeds", allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("BreedService: delete breed with id: " + id);
        breedRepository.deleteById(id);
    }
}
