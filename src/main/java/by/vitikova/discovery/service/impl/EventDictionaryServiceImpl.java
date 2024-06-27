package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.EventDictionaryDto;
import by.vitikova.discovery.converter.EventDictionaryConverter;
import by.vitikova.discovery.create.EventDictionaryCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.model.entity.Notification;
import by.vitikova.discovery.model.entity.NotificationTime;
import by.vitikova.discovery.repository.EventDictionaryRepository;
import by.vitikova.discovery.repository.NotificationRepository;
import by.vitikova.discovery.repository.NotificationTimeRepository;
import by.vitikova.discovery.service.EventDictionaryService;
import by.vitikova.discovery.update.EventDictionaryUpdateDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static by.vitikova.discovery.constant.Constant.DEFAULT_DICTIONARY_UUID;

@Service
@AllArgsConstructor
public class EventDictionaryServiceImpl implements EventDictionaryService {

    private static final Logger logger = LoggerFactory.getLogger(EventDictionaryServiceImpl.class);
    private EventDictionaryRepository eventDictionaryRepository;
    private NotificationTimeRepository notificationTimeRepository;
    private NotificationRepository notificationRepository;
    private EventDictionaryConverter eventDictionaryConverter;

    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект EventDictionaryDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "dictionary", key = "#id")
    @Override
    public EventDictionaryDto findById(Long id) {
        logger.info("EventDictionaryService: find dictionary with id: " + id);
        return eventDictionaryConverter.convert(eventDictionaryRepository.findById(id).orElseThrow(EntityNotFoundException::new));

    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов EventDto, представляющих все записи.
     */
    @Override
    public List<EventDictionaryDto> findAll() {
        logger.info("EventDictionaryService: find all dictionaries");
        var eventDictionaryList = eventDictionaryRepository.findAll();
        return eventDictionaryList.stream().map(eventDictionaryConverter::convert).collect(Collectors.toList());
    }

    /**
     * Метод для получения списка объектов EventDictionaryDto, соответствующих записи с заданным идентификатором.
     *
     * @param id идентификатор записи, для которой необходимо получить список словарных объектов
     * @return список объектов EventDictionaryDto, соответствующих заданной записи
     */
    @Override
    public List<EventDictionaryDto> findAllByRecordId(Long id) {
        logger.info("EventDictionaryService: find all dictionaries by record with id: " + id);
        var eventDictionaryList = eventDictionaryRepository.findAllByRecordId(id);
        return eventDictionaryList.stream().map(eventDictionaryConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект EventCreateDto, содержащий данные для создания записи.
     * @return Объект StateDto, представляющий созданную запись.
     */
    @CacheEvict(value = "dictionaries", key = "#dto.recordId")
    @Transactional
    @Override
    public EventDictionaryDto create(EventDictionaryCreateDto dto) {
        logger.info("EventDictionaryService: create dictionary");

        if (dto.getUuid() == null) {
            dto.setUuid(DEFAULT_DICTIONARY_UUID);
        }
        var eventDictionary = eventDictionaryConverter.convert(dto);
        return eventDictionaryConverter.convert(eventDictionaryRepository.save(eventDictionary));
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект EventUpdateDto, содержащий обновленные данные для записи.
     * @return Объект EventDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "dictionaries", key = "#dto.id")
    @Transactional
    @Override
    public EventDictionaryDto update(EventDictionaryUpdateDto dto) {
        logger.info("EventDictionaryService: update dictionary with id: " + dto.getId());
        var eventDictionary = eventDictionaryRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        eventDictionaryConverter.merge(eventDictionary, dto);
        return eventDictionaryConverter.convert(eventDictionaryRepository.save(eventDictionary));
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "dictionaries", allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {

        var notificationTimes = notificationTimeRepository.findNotificationTimesByEventId(id);
        var setId = notificationTimes.stream()
                .map(NotificationTime::getNotification)
                .map(Notification::getId)
                .collect(Collectors.toSet());
        notificationRepository.deleteAllById(setId);

        logger.info("EventDictionaryService: delete dictionary with id: " + id);
        eventDictionaryRepository.deleteById(id);
    }
}
