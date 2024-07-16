package by.vitikova.discovery.converter;

import by.vitikova.discovery.EventDto;
import by.vitikova.discovery.create.EventCreateDto;
import by.vitikova.discovery.model.entity.Event;
import by.vitikova.discovery.update.EventUpdateDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface EventConverter {

    /**
     * Преобразование объекта Event в объект EventDto.
     *
     * @param source исходный объект Event
     * @return преобразованный объект EventDto
     */
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "textColor", source = "textColor"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "dateCreated", source = "dateCreated"),
            @Mapping(target = "dictionaryId", source = "source.dictionary.id")
    })
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
     * @param event объект Event, который нужно обновить
     * @param dto   объект EventUpdateDto с обновленными данными
     * @return обновленный объект Event
     */
    Event merge(@MappingTarget Event event, EventUpdateDto dto);
}