package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalTypeRepository extends JpaRepository<AnimalType, Long> {

    boolean existsByName(String name);
}
