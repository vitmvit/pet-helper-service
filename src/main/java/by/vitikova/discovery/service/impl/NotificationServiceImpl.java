package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.NotificationDto;
import by.vitikova.discovery.converter.NotificationConverter;
import by.vitikova.discovery.create.NotificationCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.model.entity.Notification;
import by.vitikova.discovery.repository.NotificationRepository;
import by.vitikova.discovery.service.NotificationService;
import by.vitikova.discovery.update.NotificationUpdateDto;
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
public class NotificationServiceImpl implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private NotificationRepository notificationRepository;
    private NotificationConverter notificationConverter;

    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект NotificationDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "notification", key = "#id")
    @Override
    public NotificationDto findById(Long id) {
        logger.info("NotificationService: find notification with id: " + id);
        return notificationConverter.convert(notificationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Возвращает все записи по логину пользователя.
     *
     * @return Список объектов NotificationDto, представляющих все записи.
     */
    @Override
    public List<NotificationDto> findByUserLogin(String userLogin) {
        logger.info("NotificationService: find all notification by user login: " + userLogin);
        var notificationList = notificationRepository.findByUserLogin(userLogin);
        return notificationList.stream().map(notificationConverter::convert).collect(Collectors.toList());
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов NotificationDto, представляющих все записи.
     */
    @Override
    public List<NotificationDto> findAll() {
        logger.info("NotificationService: find all notification");
        var notificationList = notificationRepository.findAll();
        return notificationList.stream().map(notificationConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект NotificationCreateDto, содержащий данные для создания записи.
     * @return Объект NotificationDto, представляющий созданную запись.
     */
    @CacheEvict(value = "notifications", key = "#dto.name")
    @Transactional
    @Override
    public NotificationDto create(NotificationCreateDto dto) {
        logger.info("NotificationService: create notification");
        var event = notificationConverter.convert(dto);
        return notificationConverter.convert(notificationRepository.save(event));
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект NotificationTimeUpdateDto, содержащий обновленные данные для записи.
     * @return Объект NotificationTimeDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "notifications", key = "#dto.id")
    @Transactional
    @Override
    public NotificationDto update(NotificationUpdateDto dto) {
        logger.info("NotificationService: update notification with id: " + dto.getId());
        var notification = notificationRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        notificationConverter.merge(notification, dto);
        return notificationConverter.convert(notificationRepository.save(notification));
    }

    @Transactional
    @Override
    public NotificationDto updateStatus(Long id, boolean status) {
        var notification = notificationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        notification.setActive(status);
        return notificationConverter.convert(notificationRepository.save(notification));
    }

    @Transactional
    @Override
    public void deleteByUserLogin(String login) {
        var notificationList = notificationRepository.findByUserLogin(login);
        for (Notification item : notificationList) {
            delete(item.getId());
        }
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "notification", allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("NotificationService: delete notification with id: " + id);
        notificationRepository.deleteById(id);
    }
}
