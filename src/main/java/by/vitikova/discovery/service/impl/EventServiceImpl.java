package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.EventDto;
import by.vitikova.discovery.converter.EventConverter;
import by.vitikova.discovery.create.EventCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.EventDictionaryRepository;
import by.vitikova.discovery.repository.EventRepository;
import by.vitikova.discovery.service.EventService;
import by.vitikova.discovery.update.EventUpdateDto;
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
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    private EventRepository eventRepository;
    private EventDictionaryRepository eventDictionaryRepository;
    private EventConverter eventConverter;

    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект EventDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "event", key = "#id")
    @Override
    public EventDto findById(Long id) {
        logger.info("EventService: find event with id: " + id);
        return eventConverter.convert(eventRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Метод для получения списка объектов EventDto, соответствующих словарю с заданным идентификатором.
     *
     * @param id идентификатор словаря, для которого необходимо получить список состояний
     * @return список объектов EventDto, соответствующих заданному словарю
     */
    @Override
    public List<EventDto> findByDictionaryId(Long id) {
        logger.info("EventService: find stats with dictionary id: " + id);
        var eventList = eventRepository.findStateByDictionary_Id(id);
        return eventList.stream().map(eventConverter::convert).collect(Collectors.toList());
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов EventDto, представляющих все записи.
     */
    @Override
    public List<EventDto> findAll() {
        logger.info("EventService: find all events");
        var stateList = eventRepository.findAll();
        return stateList.stream().map(eventConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект EventCreateDto, содержащий данные для создания записи.
     * @return Объект EventDto, представляющий созданную запись.
     */
    @CacheEvict(value = "events", key = "#dto.dictionaryId")
    @Override
    public EventDto create(EventCreateDto dto) {
        logger.info("EventService: create event");
        var dict = eventDictionaryRepository.findById(dto.getDictionaryId()).orElseThrow(EntityNotFoundException::new);
        var event = eventConverter.convert(dto);
        event.setDictionary(dict);
        return eventConverter.convert(eventRepository.save(event));
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект EventUpdateDto, содержащий обновленные данные для записи.
     * @return Объект EventDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "events", key = "#dto.id")
    @Override
    public EventDto update(EventUpdateDto dto) {
        logger.info("EventService: update event with id: " + dto.getId());
        var event = eventRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        eventConverter.merge(event, dto);
        return eventConverter.convert(eventRepository.save(event));
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "events", allEntries = true)
    @Override
    public void delete(Long id) {
        logger.info("EventService: delete event with id: " + id);
        eventRepository.deleteById(id);
    }
}
