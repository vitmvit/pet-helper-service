package by.vitikova.discovery.listener;

import by.vitikova.discovery.model.entity.Notification;
import by.vitikova.discovery.model.entity.Record;
import jakarta.persistence.PrePersist;

public class NotificationListener {

    /**
     * Вызывается перед сохранением новой сущности Notification в базе данных и устанавливает isActive для нее.
     *
     * @param notification Сущность Notification, которая будет сохранена.
     */
    @PrePersist
    public void persist(Notification notification) {
        notification.setActive(true);
    }
}