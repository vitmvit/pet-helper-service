package by.vitikova.discovery.converter;

import by.vitikova.discovery.StateDto;
import by.vitikova.discovery.create.StateCreateDto;
import by.vitikova.discovery.model.entity.State;
import by.vitikova.discovery.update.StateUpdateDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface StateConverter {

    /**
     * Преобразование объекта State в объект StateDto.
     *
     * @param source исходный объект State
     * @return преобразованный объект StateDto
     */
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "value", source = "value"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "dictionaryId", source = "source.dictionary.id")
    })
    StateDto convert(State source);

    /**
     * Преобразование объекта StateCreateDto в объект State.
     *
     * @param source исходный объект StateCreateDto для создания чата
     * @return преобразованный объект State
     */
    State convert(StateCreateDto source);

    /**
     * Преобразование объекта StateUpdateDto в объект State.
     *
     * @param source исходный объект StateUpdateDto
     * @return преобразованный объект State
     */
    State convert(StateUpdateDto source);

    /**
     * Обновление полей объекта State на основе данных из StateUpdateDto.
     *
     * @param state объект State, который нужно обновить
     * @param dto   объект StateUpdateDto с обновленными данными
     * @return обновленный объект State
     */
    State merge(@MappingTarget State state, StateUpdateDto dto);
}