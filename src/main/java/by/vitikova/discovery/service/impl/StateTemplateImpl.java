package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.StateTemplateDto;
import by.vitikova.discovery.converter.StateTemplateConverter;
import by.vitikova.discovery.create.StateTemplateCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.StateTemplateRepository;
import by.vitikova.discovery.service.StateTemplateService;
import by.vitikova.discovery.update.StateTemplateUpdateDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для работы с шаблонами состояний.
 */
@Service
@AllArgsConstructor
public class StateTemplateImpl implements StateTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(StateTemplateImpl.class);
    private StateTemplateRepository stateTemplateRepository;
    private StateTemplateConverter stateTemplateConverter;


    /**
     * Метод для получения шаблона состояния по его идентификатору.
     *
     * @param id идентификатор шаблона состояния
     * @return объект StateTemplateDto, соответствующий шаблону состояния с указанным идентификатором
     * @throws EntityNotFoundException если шаблон состояния с указанным идентификатором не найден
     */
    @Cacheable(value = "template", key = "#id")
    @Override
    public StateTemplateDto findById(Long id) {
        logger.info("StateTemplateService: find state with id: " + id);
        return stateTemplateConverter.convert(stateTemplateRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Метод для получения списка всех шаблонов состояний.
     *
     * @return список объектов StateTemplateDto, соответствующих всем шаблонам состояний
     */
    @Override
    public List<StateTemplateDto> findAll() {
        logger.info("StateTemplateService: find all states");
        var stateList = stateTemplateRepository.findAll();
        return stateList.stream().map(stateTemplateConverter::convert).collect(Collectors.toList());
    }

    /**
     * Метод для создания нового шаблона состояния.
     *
     * @param dto объект StateTemplateCreateDto, содержащий данные для создания шаблона состояния
     * @return объект StateTemplateDto, соответствующий созданному шаблону состояния
     */
    @CacheEvict(value = "templates", key = "#dto.name")
    @Transactional
    @Override
    public StateTemplateDto create(StateTemplateCreateDto dto) {
        logger.info("StateTemplateService: create state");
        var record = stateTemplateConverter.convert(dto);
        return stateTemplateConverter.convert(stateTemplateRepository.save(record));
    }

    /**
     * Метод для обновления существующего шаблона состояния.
     *
     * @param dto объект StateTemplateUpdateDto, содержащий данные для обновления шаблона состояния
     * @return объект StateTemplateDto, соответствующий обновленному шаблону состояния
     * @throws EntityNotFoundException если шаблон состояния с указанным идентификатором не найден
     */
    @CacheEvict(value = "templates", key = "#dto.id")
    @Transactional
    @Override
    public StateTemplateDto update(StateTemplateUpdateDto dto) {
        logger.info("StateTemplateService: update state with id: " + dto.getId());
        var record = stateTemplateRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        stateTemplateConverter.merge(record, dto);
        return stateTemplateConverter.convert(stateTemplateRepository.save(record));
    }

    /**
     * Метод для удаления шаблона состояния по его идентификатору.
     *
     * @param id идентификатор шаблона состояния, который нужно удалить
     */
    @CacheEvict(value = "templates", allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("StateTemplateService: delete state with id: " + id);
        stateTemplateRepository.deleteById(id);
    }
}

