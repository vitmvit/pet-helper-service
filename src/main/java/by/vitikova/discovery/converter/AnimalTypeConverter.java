package by.vitikova.discovery.converter;

import by.vitikova.discovery.AnimalTypeDto;
import by.vitikova.discovery.create.AnimalTypeCreateDto;
import by.vitikova.discovery.model.entity.AnimalType;
import by.vitikova.discovery.update.AnimalTypeUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface AnimalTypeConverter {

    /**
     * Преобразование объекта AnimalType в объект AnimalTypeDto.
     *
     * @param source исходный объект AnimalType
     * @return преобразованный объект AnimalTypeDto
     */
    AnimalTypeDto convert(AnimalType source);

    /**
     * Преобразование объекта AnimalTypeCreateDto в объект AnimalType.
     *
     * @param source исходный объект AnimalTypeCreateDto для создания чата
     * @return преобразованный объект AnimalType
     */
    AnimalType convert(AnimalTypeCreateDto source);

    /**
     * Преобразование объекта StateUpdateDto в объект AnimalType.
     *
     * @param source исходный объект AnimalTypeUpdateDto
     * @return преобразованный объект AnimalType
     */
    AnimalType convert(AnimalTypeUpdateDto source);

    /**
     * Обновление полей объекта AnimalType на основе данных из AnimalTypeUpdateDto.
     *
     * @param record объект AnimalType, который нужно обновить
     * @param dto    объект AnimalTypeUpdateDto с обновленными данными
     * @return обновленный объект AnimalType
     */
    AnimalType merge(@MappingTarget AnimalType record, AnimalTypeUpdateDto dto);
}
