package by.vitikova.discovery.listener;

import by.vitikova.discovery.model.entity.Record;
import jakarta.persistence.PrePersist;

public class RecordListener {

    /**
     * Вызывается перед сохранением новой сущности Record в базе данных и устанавливает hasPedigree для нее.
     *
     * @param record Сущность Record, которая будет сохранена.
     */
    @PrePersist
    public void persist(Record record) {
        record.setHasPedigree(false);
    }
}