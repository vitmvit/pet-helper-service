package by.vitikova.discovery.repository;

import by.vitikova.discovery.constant.ArticleStatus;
import by.vitikova.discovery.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByRedactorName(String name);

    List<Article> findAllByStatus(ArticleStatus status);
}
