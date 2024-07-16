package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.Pedigree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedigreeRepository extends JpaRepository<Pedigree, Long> {

    Optional<Pedigree> findPedigreeByRecordId(Long id);
}
