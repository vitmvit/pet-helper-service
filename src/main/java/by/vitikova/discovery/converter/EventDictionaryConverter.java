package by.vitikova.discovery.converter;

import by.vitikova.discovery.EventDictionaryDto;
import by.vitikova.discovery.create.EventDictionaryCreateDto;
import by.vitikova.discovery.model.entity.EventDictionary;
import by.vitikova.discovery.update.EventDictionaryUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface EventDictionaryConverter {

    /**
     * Преобразование объекта EventDictionary в объект EventDictionaryDto.
     *
     * @param source исходный объект EventDictionary
     * @return преобразованный объект EventDictionaryDto
     */
    EventDictionaryDto convert(EventDictionary source);

    /**
     * Преобразование объекта EventDictionaryCreateDto в объект EventDictionary.
     *
     * @param source исходный объект EventDictionaryCreateDto для создания чата
     * @return преобразованный объект EventDictionary
     */
    EventDictionary convert(EventDictionaryCreateDto source);

    /**
     * Преобразование объекта EventDictionaryUpdateDto в объект EventDictionary.
     *
     * @param source исходный объект EventDictionaryUpdateDto
     * @return преобразованный объект EventDictionary
     */
    EventDictionary convert(EventDictionaryUpdateDto source);

    /**
     * Обновление полей объекта EventDictionary на основе данных из EventUpdateDto.
     *
     * @param record объект EventDictionary, который нужно обновить
     * @param dto    объект EventUpdateDto с обновленными данными
     * @return обновленный объект EventDictionary
     */
    EventDictionary merge(@MappingTarget EventDictionary record, EventDictionaryUpdateDto dto);
}
