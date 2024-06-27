package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.NotExistParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotExistParentRepository extends JpaRepository<NotExistParent, Long> {

    void deleteById(Long id);
}
