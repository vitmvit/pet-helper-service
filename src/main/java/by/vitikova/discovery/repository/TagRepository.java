package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByArticle_Id(Long id);

    Optional<Tag> findByName(String name);

    void deleteTagsByArticleId(Long id);
}
