package by.vitikova.discovery.converter;

import by.vitikova.discovery.StateTemplateDto;
import by.vitikova.discovery.create.StateTemplateCreateDto;
import by.vitikova.discovery.model.entity.StateTemplate;
import by.vitikova.discovery.update.StateTemplateUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface StateTemplateConverter {

    /**
     * Преобразование объекта StateTemplateDto в объект StateDto.
     *
     * @param source исходный объект StateTemplateDto
     * @return преобразованный объект StateDto
     */
    StateTemplateDto convert(StateTemplate source);

    /**
     * Преобразование объекта StateTemplateCreateDto в объект StateTemplateDto.
     *
     * @param source исходный объект StateTemplateCreateDto для создания чата
     * @return преобразованный объект StateTemplateDto
     */
    StateTemplate convert(StateTemplateCreateDto source);

    /**
     * Преобразование объекта StateTemplateUpdateDto в объект StateTemplateDto.
     *
     * @param source исходный объект StateTemplateUpdateDto
     * @return преобразованный объект StateTemplateDto
     */
    StateTemplate convert(StateTemplateUpdateDto source);

    /**
     * Обновление полей объекта StateTemplateDto на основе данных из StateTemplateUpdateDto.
     *
     * @param record объект StateTemplateDto, который нужно обновить
     * @param dto    объект StateTemplateUpdateDto с обновленными данными
     * @return обновленный объект StateTemplateDto
     */
    StateTemplate merge(@MappingTarget StateTemplate record, StateTemplateUpdateDto dto);
}
