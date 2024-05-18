package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.EventDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDictionaryRepository extends JpaRepository<EventDictionary, Long> {

    List<EventDictionary> findAllByRecordId(Long id);
}
