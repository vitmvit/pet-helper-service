package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.NotificationTimeDto;
import by.vitikova.discovery.converter.NotificationTimeConverter;
import by.vitikova.discovery.create.NotificationTimeCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.NotificationRepository;
import by.vitikova.discovery.repository.NotificationTimeRepository;
import by.vitikova.discovery.service.NotificationTimeService;
import by.vitikova.discovery.update.NotificationTimeUpdateDto;
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
public class NotificationTimeServiceImpl implements NotificationTimeService {

    private NotificationTimeRepository notificationTimeRepository;
    private NotificationRepository notificationRepository;
    private static final Logger logger = LoggerFactory.getLogger(NotificationTimeServiceImpl.class);
    private NotificationTimeConverter notificationTimeConverter;

    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект NotificationTimeDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "notification_time", key = "#id")
    @Override
    public NotificationTimeDto findById(Long id) {
        logger.info("NotificationTimeService: find notification time with id: " + id);
        return notificationTimeConverter.convert(notificationTimeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Метод для получения списка объектов NotificationTimeDto, соответствующих словарю с заданным идентификатором.
     *
     * @param id идентификатор словаря, для которого необходимо получить список состояний
     * @return список объектов NotificationTimeDto, соответствующих заданному словарю
     */
    @Override
    public List<NotificationTimeDto> findByNotificationId(Long id) {
        logger.info("NotificationTimeService: find notifications time with notification id: " + id);
        var notificationTimeList = notificationTimeRepository.findNotificationTimeByNotification_Id(id);
        return notificationTimeList.stream().map(notificationTimeConverter::convert).collect(Collectors.toList());
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов NotificationTimeDto, представляющих все записи.
     */
    @Override
    public List<NotificationTimeDto> findAll() {
        logger.info("NotificationTimeService: find all notification times");
        var notificationTimeList = notificationTimeRepository.findAll();
        return notificationTimeList.stream().map(notificationTimeConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект NotificationTimeCreateDto, содержащий данные для создания записи.
     * @return Объект NotificationTimeDto, представляющий созданную запись.
     */
    @CacheEvict(value = "notification_times", key = "#dto.time")
    @Transactional
    @Override
    public NotificationTimeDto create(NotificationTimeCreateDto dto) {
        logger.info("NotificationTimeService: create notification time");
        var notification = notificationRepository.findById(dto.getNotificationId()).orElseThrow(EntityNotFoundException::new);
        var event = notificationTimeConverter.convert(dto);
        event.setNotification(notification);
        return notificationTimeConverter.convert(notificationTimeRepository.save(event));
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект NotificationTimeUpdateDto, содержащий обновленные данные для записи.
     * @return Объект NotificationTimeDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "notification_times", key = "#dto.id")
    @Transactional
    @Override
    public NotificationTimeDto update(NotificationTimeUpdateDto dto) {
        logger.info("NotificationTimeService: update notification time with id: " + dto.getId());
        var notificationTime = notificationTimeRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        notificationTimeConverter.merge(notificationTime, dto);
        return notificationTimeConverter.convert(notificationTimeRepository.save(notificationTime));
    }


    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "notification_times", allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("NotificationTimeService: delete notification time with id: " + id);
        notificationTimeRepository.deleteById(id);
    }
}