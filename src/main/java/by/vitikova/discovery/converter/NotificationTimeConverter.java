package by.vitikova.discovery.converter;

import by.vitikova.discovery.NotificationTimeDto;
import by.vitikova.discovery.create.NotificationTimeCreateDto;
import by.vitikova.discovery.model.entity.NotificationTime;
import by.vitikova.discovery.update.NotificationTimeUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface NotificationTimeConverter {

    /**
     * Преобразование объекта NotificationTime в объект NotificationTimeDto.
     *
     * @param source исходный объект NotificationTime
     * @return преобразованный объект NotificationTimeDto
     */
    NotificationTimeDto convert(NotificationTime source);

    /**
     * Преобразование объекта NotificationTimeCreateDto в объект NotificationTime.
     *
     * @param source исходный объект NotificationTimeCreateDto для создания чата
     * @return преобразованный объект NotificationTime
     */
    NotificationTime convert(NotificationTimeCreateDto source);

    /**
     * Преобразование объекта NotificationTimeUpdateDto в объект NotificationTime.
     *
     * @param source исходный объект NotificationTimeUpdateDto
     * @return преобразованный объект NotificationTime
     */
    NotificationTime convert(NotificationTimeUpdateDto source);

    /**
     * Обновление полей объекта NotificationTime на основе данных из NotificationTimeUpdateDto.
     *
     * @param record объект NotificationTime, который нужно обновить
     * @param dto    объект NotificationTimeUpdateDto с обновленными данными
     * @return обновленный объект NotificationTime
     */
    NotificationTime merge(@MappingTarget NotificationTime record, NotificationTimeUpdateDto dto);
}
