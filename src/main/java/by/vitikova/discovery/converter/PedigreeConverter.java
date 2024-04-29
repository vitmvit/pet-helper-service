package by.vitikova.discovery.converter;

import by.vitikova.discovery.PedigreeDto;
import by.vitikova.discovery.create.PedigreeCreateDto;
import by.vitikova.discovery.model.entity.Pedigree;
import by.vitikova.discovery.update.PedigreeUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface PedigreeConverter {

    /**
     * Преобразование объекта Pedigree в объект PedigreeDto.
     *
     * @param source исходный объект Pedigree
     * @return преобразованный объект PedigreeDto
     */
    PedigreeDto convert(Pedigree source);

    /**
     * Преобразование объекта PedigreeCreateDto в объект Pedigree.
     *
     * @param source исходный объект PedigreeCreateDto для создания чата
     * @return преобразованный объект Pedigree
     */
    Pedigree convert(PedigreeCreateDto source);

    /**
     * Преобразование объекта PedigreeUpdateDto в объект Pedigree.
     *
     * @param source исходный объект PedigreeUpdateDto
     * @return преобразованный объект Pedigree
     */
    Pedigree convert(PedigreeUpdateDto source);

    /**
     * Обновление полей объекта Pedigree на основе данных из PedigreeUpdateDto.
     *
     * @param record объект Pedigree, который нужно обновить
     * @param dto    объект PedigreeUpdateDto с обновленными данными
     * @return обновленный объект Pedigree
     */
    Pedigree merge(@MappingTarget Pedigree record, PedigreeUpdateDto dto);
}
