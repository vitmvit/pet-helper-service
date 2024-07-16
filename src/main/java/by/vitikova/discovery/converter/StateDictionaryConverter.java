package by.vitikova.discovery.converter;

import by.vitikova.discovery.StateDictionaryDto;
import by.vitikova.discovery.create.StateDictionaryCreateDto;
import by.vitikova.discovery.model.entity.StateDictionary;
import by.vitikova.discovery.update.StateDictionaryUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface StateDictionaryConverter {

    /**
     * Преобразование объекта StateDictionary в объект StateDictionaryDto.
     *
     * @param source исходный объект StateDictionary
     * @return преобразованный объект StateDictionaryDto
     */
    StateDictionaryDto convert(StateDictionary source);

    /**
     * Преобразование объекта StateDictionaryCreateDto в объект StateDictionary.
     *
     * @param source исходный объект StateDictionaryCreateDto для создания чата
     * @return преобразованный объект StateDictionary
     */
    StateDictionary convert(StateDictionaryCreateDto source);

    /**
     * Преобразование объекта StateDictionaryUpdateDto в объект StateDictionary.
     *
     * @param source исходный объект StateDictionaryUpdateDto
     * @return преобразованный объект StateDictionary
     */
    StateDictionary convert(StateDictionaryUpdateDto source);

    /**
     * Обновление полей объекта StateDictionary на основе данных из StateUpdateDto.
     *
     * @param stateDictionary объект StateDictionary, который нужно обновить
     * @param dto             объект StateUpdateDto с обновленными данными
     * @return обновленный объект StateDictionary
     */
    StateDictionary merge(@MappingTarget StateDictionary stateDictionary, StateDictionaryUpdateDto dto);
}