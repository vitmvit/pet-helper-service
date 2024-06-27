package by.vitikova.discovery.service;

import by.vitikova.discovery.NotificationTimeDto;
import by.vitikova.discovery.create.NotificationTimeCreateDto;
import by.vitikova.discovery.update.NotificationTimeUpdateDto;

import java.util.List;

public interface NotificationTimeService {

    NotificationTimeDto findById(Long id);

    List<NotificationTimeDto> findByNotificationId(Long id);

    List<NotificationTimeDto> findAll();

    NotificationTimeDto create(NotificationTimeCreateDto dto);

    NotificationTimeDto update(NotificationTimeUpdateDto dto);

    void delete(Long id);
}
