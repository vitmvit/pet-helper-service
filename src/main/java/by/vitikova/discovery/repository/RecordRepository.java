package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findRecordByUserLogin(String login);
}
