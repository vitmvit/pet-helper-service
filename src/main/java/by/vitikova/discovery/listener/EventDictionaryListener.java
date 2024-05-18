package by.vitikova.discovery.listener;

import by.vitikova.discovery.model.entity.EventDictionary;
import jakarta.persistence.PrePersist;

public class EventDictionaryListener {

    /**
     * Вызывается перед сохранением новой сущности EventDictionary в базе данных и устанавливает isActive для нее.
     *
     * @param eventDictionary Сущность EventDictionary, которая будет сохранена.
     */
    @PrePersist
    public void persist(EventDictionary eventDictionary) {
        eventDictionary.setActive(true);
    }
}
