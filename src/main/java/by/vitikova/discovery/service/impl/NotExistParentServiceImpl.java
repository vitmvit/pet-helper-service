package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.NotExistParentDto;
import by.vitikova.discovery.converter.NotExistParentConverter;
import by.vitikova.discovery.create.NotExistParentCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.NotExistParentRepository;
import by.vitikova.discovery.service.NotExistParentService;
import by.vitikova.discovery.update.NotExistParentUpdateDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс NotExistParentServiceImpl реализует интерфейс NotExistParentService
 * и предоставляет методы для работы с объектами NotExistParent в базе данных.
 */
@Service
@AllArgsConstructor
public class NotExistParentServiceImpl implements NotExistParentService {

    private static final Logger logger = LoggerFactory.getLogger(NotExistParentServiceImpl.class);
    private NotExistParentRepository notExistParentRepository;
    private NotExistParentConverter notExistParentConverter;

    /**
     * Находит объект NotExistParent по его идентификатору.
     *
     * @param id идентификатор объекта NotExistParent
     * @return объект NotExistParentDto, соответствующий найденному NotExistParent
     * @throws EntityNotFoundException если объект NotExistParent не найден
     */
    @Cacheable(value = "parent", key = "#id")
    @Override
    public NotExistParentDto findById(Long id) {
        logger.info("NotExistParentService: find parent with id: " + id);
        return notExistParentConverter.convert(notExistParentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Находит все объекты NotExistParent в базе данных.
     *
     * @return список всех объектов NotExistParentDto
     */
    @Override
    public List<NotExistParentDto> findAll() {
        logger.info("NotExistParentService: find all parents");
        var listRecord = notExistParentRepository.findAll();
        return listRecord.stream().map(notExistParentConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новый объект NotExistParent в базе данных.
     *
     * @param dto объект NotExistParentCreateDto, содержащий данные для создания нового NotExistParent
     * @return объект NotExistParentDto, соответствующий созданному NotExistParent
     */
    @CacheEvict(value = "parents", key = "#dto.name")
    @Override
    public NotExistParentDto create(NotExistParentCreateDto dto) {
        logger.info("NotExistParentService: create parent");
        var pedigree = notExistParentConverter.convert(dto);
        return notExistParentConverter.convert(notExistParentRepository.save(pedigree));
    }

    /**
     * Обновляет существующий объект NotExistParent в базе данных.
     *
     * @param dto объект NotExistParentUpdateDto, содержащий данные для обновления NotExistParent
     * @return объект NotExistParentDto, соответствующий обновленному NotExistParent
     * @throws EntityNotFoundException если объект NotExistParent не найден
     */
    @CacheEvict(value = "parents", key = "#dto.id")
    @Override
    public NotExistParentDto update(NotExistParentUpdateDto dto) {
        logger.info("NotExistParentService: update parent");
        var record = notExistParentRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        notExistParentConverter.merge(record, dto);

        return notExistParentConverter.convert(notExistParentRepository.save(record));
    }

    /**
     * Удаляет объект NotExistParent из базы данных по его идентификатору.
     *
     * @param id идентификатор объекта NotExistParent, который нужно удалить
     */
    @CacheEvict(value = "parents", allEntries = true)
    @Override
    public void delete(Long id) {
        logger.info("NotExistParentService: delete parent with id: " + id);
        notExistParentRepository.deleteById(id);
    }
}
