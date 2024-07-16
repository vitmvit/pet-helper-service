package by.vitikova.discovery.service;

import by.vitikova.discovery.NotificationDto;
import by.vitikova.discovery.create.NotificationCreateDto;
import by.vitikova.discovery.update.NotificationUpdateDto;

import java.util.List;

public interface NotificationService {

    NotificationDto findById(Long id);

    List<NotificationDto> findByUserLogin(String userLogin);

    List<NotificationDto> findAll();

    NotificationDto create(NotificationCreateDto dto);

    NotificationDto update(NotificationUpdateDto dto);

    NotificationDto updateStatus(Long id, boolean status);

    void deleteByUserLogin(String login);

    void delete(Long id);
}