package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {

    List<Breed> findBreedByType_Id(Long id);
}
