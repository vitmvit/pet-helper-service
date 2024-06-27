package by.vitikova.discovery.converter;

import by.vitikova.discovery.VaccinationTypeDto;
import by.vitikova.discovery.create.VaccinationTypeCreateDto;
import by.vitikova.discovery.model.entity.VaccinationType;
import by.vitikova.discovery.update.VaccinationTypeUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VaccinationTypeConverter {
    /**
     * Преобразование объекта VaccinationType в объект VaccinationTypeDto.
     *
     * @param source исходный объект VaccinationType
     * @return преобразованный объект VaccinationTypeDto
     */
    VaccinationTypeDto convert(VaccinationType source);

    /**
     * Преобразование объекта VaccinationTypeCreateDto в объект VaccinationType.
     *
     * @param source исходный объект VaccinationTypeCreateDto для создания чата
     * @return преобразованный объект VaccinationType
     */
    VaccinationType convert(VaccinationTypeCreateDto source);

    /**
     * Преобразование объекта VaccinationUpdateDto в объект VaccinationType.
     *
     * @param source исходный объект VaccinationTypeUpdateDto
     * @return преобразованный объект VaccinationType
     */
    VaccinationType convert(VaccinationTypeUpdateDto source);

    /**
     * Обновление полей объекта VaccinationType на основе данных из VaccinationTypeUpdateDto.
     *
     * @param type объект VaccinationType, который нужно обновить
     * @param dto  объект VaccinationTypeUpdateDto с обновленными данными
     * @return обновленный объект VaccinationType
     */
    VaccinationType merge(@MappingTarget VaccinationType type, VaccinationTypeUpdateDto dto);
}
