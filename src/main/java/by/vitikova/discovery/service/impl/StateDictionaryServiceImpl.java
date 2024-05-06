package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.StateDictionaryDto;
import by.vitikova.discovery.converter.StateDictionaryConverter;
import by.vitikova.discovery.create.StateDictionaryCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.StateDictionaryRepository;
import by.vitikova.discovery.service.StateDictionaryService;
import by.vitikova.discovery.update.StateDictionaryUpdateDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static by.vitikova.discovery.constant.Constant.DEFAULT_DICTIONARY_UUID;

@Service
@AllArgsConstructor
public class StateDictionaryServiceImpl implements StateDictionaryService {

    private static final Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class);
    private StateDictionaryRepository stateDictionaryRepository;
    private StateDictionaryConverter stateConverter;

    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект StateDictionaryDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "dictionary", key = "#id")
    @Override
    public StateDictionaryDto findById(Long id) {
        logger.info("StateDictionaryService: find dictionary with id: " + id);
        return stateConverter.convert(stateDictionaryRepository.findById(id).orElseThrow(EntityNotFoundException::new));

    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов StateDto, представляющих все записи.
     */
    @Override
    public List<StateDictionaryDto> findAll() {
        logger.info("StateDictionaryService: find all dictionaries");
        var stateList = stateDictionaryRepository.findAll();
        return stateList.stream().map(stateConverter::convert).collect(Collectors.toList());
    }

    /**
     * Метод для получения списка объектов StateDictionaryDto, соответствующих записи с заданным идентификатором.
     *
     * @param id идентификатор записи, для которой необходимо получить список словарных объектов
     * @return список объектов StateDictionaryDto, соответствующих заданной записи
     */
    @Override
    public List<StateDictionaryDto> findAllByRecordId(Long id) {
        logger.info("StateDictionaryService: find all dictionaries by record with id: " + id);
        var stateList = stateDictionaryRepository.findAllByRecordId(id);
        return stateList.stream().map(stateConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект StateCreateDto, содержащий данные для создания записи.
     * @return Объект StateDto, представляющий созданную запись.
     */
    @CacheEvict(value = "dictionaries", key = "#dto.recordId")
    @Override
    public StateDictionaryDto create(StateDictionaryCreateDto dto) {
        logger.info("StateDictionaryService: create dictionary");

        if (dto.getUuid() == null) {
            dto.setUuid(DEFAULT_DICTIONARY_UUID);
        }
        var record = stateConverter.convert(dto);
        return stateConverter.convert(stateDictionaryRepository.save(record));
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект StateUpdateDto, содержащий обновленные данные для записи.
     * @return Объект StateDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "dictionaries", key = "#dto.id")
    @Override
    public StateDictionaryDto update(StateDictionaryUpdateDto dto) {
        logger.info("StateDictionaryService: update dictionary with id: " + dto.getId());
        var record = stateDictionaryRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        stateConverter.merge(record, dto);
        return stateConverter.convert(stateDictionaryRepository.save(record));
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "dictionaries", allEntries = true)
    @Override
    public void delete(Long id) {
        logger.info("StateDictionaryService: delete dictionary with id: " + id);
        stateDictionaryRepository.deleteById(id);
    }
}
