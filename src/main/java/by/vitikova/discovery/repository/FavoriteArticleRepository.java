package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.FavoriteArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteArticleRepository extends JpaRepository<FavoriteArticle, Long> {

    List<FavoriteArticle> findByUserLogin(String login);

    Optional<FavoriteArticle> findByArticleId(Long id);

    void deleteFavoriteArticleById(Long id);
}
