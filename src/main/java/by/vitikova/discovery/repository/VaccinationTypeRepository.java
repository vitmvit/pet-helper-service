package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.VaccinationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationTypeRepository extends JpaRepository<VaccinationType, Long> {

    boolean existsByName(String name);
}
