package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.StateTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateTemplateRepository extends JpaRepository<StateTemplate, Long> {

}
