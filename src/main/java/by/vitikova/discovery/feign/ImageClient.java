package by.vitikova.discovery.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign-клиент для взаимодействия с сервисом изображений.
 */
@FeignClient(contextId = "imageClient", value = "${feign.image-service.value}", url = "${feign.image-service.url}")
public interface ImageClient {

    /**
     * Удаляет изображение по его UUID.
     *
     * @param uuid UUID изображения
     */
    @DeleteMapping("/images/remove")
    void removeImage(@RequestParam String uuid);

    /**
     * Удаляет аватар по его UUID.
     *
     * @param uuid UUID аватара
     */
    @DeleteMapping("/avatars/remove")
    void removeAvatar(@RequestParam String uuid);
}
