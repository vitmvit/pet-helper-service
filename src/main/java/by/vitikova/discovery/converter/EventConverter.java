package by.vitikova.discovery.converter;

import by.vitikova.discovery.EventDto;
import by.vitikova.discovery.create.EventCreateDto;
import by.vitikova.discovery.model.entity.Event;
import by.vitikova.discovery.update.EventUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface EventConverter {

    /**
     * Преобразование объекта Event в объект EventDto.
     *
     * @param source исходный объект Event
     * @return преобразованный объект EventDto
     */
    EventDto convert(Event source);

    /**
     * Преобразование объекта EventCreateDto в объект Event.
     *
     * @param source исходный объект EventCreateDto для создания чата
     * @return преобразованный объект Event
     */
    Event convert(EventCreateDto source);

    /**
     * Преобразование объекта EventUpdateDto в объект Event.
     *
     * @param source исходный объект EventUpdateDto
     * @return преобразованный объект Event
     */
    Event convert(EventUpdateDto source);

    /**
     * Обновление полей объекта Event на основе данных из EventUpdateDto.
     *
     * @param record объект Event, который нужно обновить
     * @param dto    объект EventUpdateDto с обновленными данными
     * @return обновленный объект Event
     */
    Event merge(@MappingTarget Event record, EventUpdateDto dto);
}
