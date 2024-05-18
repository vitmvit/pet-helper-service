package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.StateDto;
import by.vitikova.discovery.converter.StateConverter;
import by.vitikova.discovery.create.StateCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.StateDictionaryRepository;
import by.vitikova.discovery.repository.StateRepository;
import by.vitikova.discovery.service.StateService;
import by.vitikova.discovery.update.StateUpdateDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StateServiceImpl implements StateService {

    private static final Logger logger = LoggerFactory.getLogger(StateServiceImpl.class);
    private StateRepository stateRepository;
    private StateDictionaryRepository stateDictionaryRepository;
    private StateConverter stateConverter;

    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект StateDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "state", key = "#id")
    @Override
    public StateDto findById(Long id) {
        logger.info("StateService: find state with id: " + id);
        return stateConverter.convert(stateRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Метод для получения списка объектов StateDto, соответствующих словарю с заданным идентификатором.
     *
     * @param id идентификатор словаря, для которого необходимо получить список состояний
     * @return список объектов StateDto, соответствующих заданному словарю
     */
    @Override
    public List<StateDto> findByDictionaryId(Long id) {
        logger.info("StateService: find stats with dictionary id: " + id);
        var stateList = stateRepository.findStateByDictionary_Id(id);
        return stateList.stream().map(stateConverter::convert).collect(Collectors.toList());
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов StateDto, представляющих все записи.
     */
    @Override
    public List<StateDto> findAll() {
        logger.info("StateService: find all states");
        var stateList = stateRepository.findAll();
        return stateList.stream().map(stateConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект StateCreateDto, содержащий данные для создания записи.
     * @return Объект StateDto, представляющий созданную запись.
     */
    @CacheEvict(value = "states", key = "#dto.dictionaryId")
    @Override
    public StateDto create(StateCreateDto dto) {
        logger.info("StateService: create state");
        var dict = stateDictionaryRepository.findById(dto.getDictionaryId()).orElseThrow(EntityNotFoundException::new);
        var state = stateConverter.convert(dto);
        state.setDictionary(dict);
        return stateConverter.convert(stateRepository.save(state));
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект StateUpdateDto, содержащий обновленные данные для записи.
     * @return Объект StateDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "states", key = "#dto.id")
    @Override
    public StateDto update(StateUpdateDto dto) {
        logger.info("StateService: update state with id: " + dto.getId());
        var record = stateRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        stateConverter.merge(record, dto);
        return stateConverter.convert(stateRepository.save(record));
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "states", allEntries = true)
    @Override
    public void delete(Long id) {
        logger.info("StateService: delete state with id: " + id);
        stateRepository.deleteById(id);
    }
}
