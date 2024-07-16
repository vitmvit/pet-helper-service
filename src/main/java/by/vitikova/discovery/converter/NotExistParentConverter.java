package by.vitikova.discovery.converter;

import by.vitikova.discovery.NotExistParentDto;
import by.vitikova.discovery.create.NotExistParentCreateDto;
import by.vitikova.discovery.model.entity.NotExistParent;
import by.vitikova.discovery.update.NotExistParentUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotExistParentConverter {

    /**
     * Преобразование объекта Pedigree в объект NotExistParentDto.
     *
     * @param source исходный объект NotExistParent
     * @return преобразованный объект NotExistParentDto
     */
    NotExistParentDto convert(NotExistParent source);

    /**
     * Преобразование объекта NotExistParentCreateDto в объект NotExistParent.
     *
     * @param source исходный объект NotExistParentCreateDto для создания чата
     * @return преобразованный объект NotExistParent
     */
    NotExistParent convert(NotExistParentCreateDto source);

    /**
     * Преобразование объекта NotExistParentUpdateDto в объект NotExistParent.
     *
     * @param source исходный объект NotExistParentUpdateDto
     * @return преобразованный объект NotExistParent
     */
    NotExistParent convert(NotExistParentUpdateDto source);

    /**
     * Обновление полей объекта NotExistParent на основе данных из NotExistParentUpdateDto.
     *
     * @param notExistParent объект NotExistParent, который нужно обновить
     * @param dto            объект NotExistParentUpdateDto с обновленными данными
     * @return обновленный объект NotExistParent
     */
    NotExistParent merge(@MappingTarget NotExistParent notExistParent, NotExistParentUpdateDto dto);
}