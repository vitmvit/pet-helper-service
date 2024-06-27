package by.vitikova.discovery.converter;

import by.vitikova.discovery.NotificationDto;
import by.vitikova.discovery.create.NotificationCreateDto;
import by.vitikova.discovery.model.entity.Notification;
import by.vitikova.discovery.update.NotificationUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface NotificationConverter {

    /**
     * Преобразование объекта Notification в объект NotificationDto.
     *
     * @param source исходный объект Notification
     * @return преобразованный объект NotificationDto
     */
    NotificationDto convert(Notification source);

    /**
     * Преобразование объекта NotificationCreateDto в объект Notification.
     *
     * @param source исходный объект NotificationCreateDto для создания чата
     * @return преобразованный объект Notification
     */
    Notification convert(NotificationCreateDto source);

    /**
     * Преобразование объекта NotificationUpdateDto в объект Notification.
     *
     * @param source исходный объект NotificationUpdateDto
     * @return преобразованный объект Notification
     */
    Notification convert(NotificationUpdateDto source);

    /**
     * Обновление полей объекта Notification на основе данных из NotificationUpdateDto.
     *
     * @param record объект Notification, который нужно обновить
     * @param dto    объект NotificationUpdateDto с обновленными данными
     * @return обновленный объект Notification
     */
    Notification merge(@MappingTarget Notification record, NotificationUpdateDto dto);
}
