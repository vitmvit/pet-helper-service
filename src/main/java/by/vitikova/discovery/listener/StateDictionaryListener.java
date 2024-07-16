package by.vitikova.discovery.listener;

import by.vitikova.discovery.model.entity.StateDictionary;
import jakarta.persistence.PrePersist;

public class StateDictionaryListener {

    /**
     * Вызывается перед сохранением новой сущности StateDictionary в базе данных и устанавливает isActive для нее.
     *
     * @param stateDictionary Сущность StateDictionary, которая будет сохранена.
     */
    @PrePersist
    public void persist(StateDictionary stateDictionary) {
        stateDictionary.setActive(true);
    }
}