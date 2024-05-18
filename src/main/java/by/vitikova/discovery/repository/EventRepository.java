package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findStateByDictionary_Id(Long id);
}
