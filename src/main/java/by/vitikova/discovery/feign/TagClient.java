package by.vitikova.discovery.feign;

import by.vitikova.discovery.TagDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Feign-клиент для взаимодействия с сервисом тегов.
 */
@FeignClient(contextId = "tagClient", value = "${feign.tag-service.value}", url = "${feign.tag-service.url}")
public interface TagClient {

    /**
     * Возвращает все теги, связанные с заданной статьей.
     *
     * @param id идентификатор статьи
     * @return список тегов, связанных с заданной статьей
     */
    @GetMapping("/article/{id}")
    ResponseEntity<List<TagDto>> findByArticleId(@PathVariable("id") Long id);
}