package by.vitikova.discovery.converter;

import by.vitikova.discovery.TagDto;
import by.vitikova.discovery.create.TagCreateDto;
import by.vitikova.discovery.model.entity.Tag;
import by.vitikova.discovery.update.TagUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface TagConverter {

    /**
     * Преобразование объекта Tag в объект TagDto.
     *
     * @param source исходный объект Tag
     * @return преобразованный объект TagDto
     */
    TagDto convert(Tag source);

    /**
     * Преобразование объекта TagCreateDto в объект Tag.
     *
     * @param source исходный объект TagCreateDto для создания чата
     * @return преобразованный объект Tag
     */
    Tag convert(TagCreateDto source);

    /**
     * Преобразование объекта TagUpdateDto в объект Tag.
     *
     * @param source исходный объект TagUpdateDto
     * @return преобразованный объект Tag
     */
    Tag convert(TagUpdateDto source);

    /**
     * Обновление полей объекта Tag на основе данных из TagUpdateDto.
     *
     * @param tag объект Tag, который нужно обновить
     * @param dto объект TagUpdateDto с обновленными данными
     * @return обновленный объект Tag
     */
    Tag merge(@MappingTarget Tag tag, TagUpdateDto dto);
}
