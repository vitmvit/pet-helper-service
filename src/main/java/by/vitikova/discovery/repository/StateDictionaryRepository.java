package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.StateDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateDictionaryRepository extends JpaRepository<StateDictionary, Long> {

    List<StateDictionary> findAllByRecordId(Long id);
}
