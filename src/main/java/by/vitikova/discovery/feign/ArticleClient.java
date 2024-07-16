package by.vitikova.discovery.feign;

import by.vitikova.discovery.ArticleDto;
import by.vitikova.discovery.constant.ArticleStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Feign-клиент для взаимодействия с сервисом статей.
 */
@FeignClient(contextId = "articleClient", value = "${feign.article-service.value}", url = "${feign.article-service.url}")
public interface ArticleClient {

    /**
     * Возвращает все статьи с заданным статусом.
     *
     * @param status статус статей
     * @return список статей с заданным статусом
     */
    @GetMapping("/editor/status/{status}")
    ResponseEntity<List<ArticleDto>> findAllByStatus(@PathVariable("status") ArticleStatus status);
}
