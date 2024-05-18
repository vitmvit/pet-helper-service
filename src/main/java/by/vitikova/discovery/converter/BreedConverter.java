package by.vitikova.discovery.converter;

import by.vitikova.discovery.BreedDto;
import by.vitikova.discovery.create.BreedCreateDto;
import by.vitikova.discovery.model.entity.Breed;
import by.vitikova.discovery.update.BreedUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface BreedConverter {

    /**
     * Преобразование объекта Breed в объект BreedDto.
     *
     * @param source исходный объект Breed
     * @return преобразованный объект BreedDto
     */
    BreedDto convert(Breed source);

    /**
     * Преобразование объекта BreedCreateDto в объект Breed.
     *
     * @param source исходный объект BreedCreateDto для создания чата
     * @return преобразованный объект Breed
     */
    Breed convert(BreedCreateDto source);

    /**
     * Преобразование объекта BreedUpdateDto в объект Breed.
     *
     * @param source исходный объект BreedUpdateDto
     * @return преобразованный объект Breed
     */
    Breed convert(BreedUpdateDto source);

    /**
     * Обновление полей объекта Breed на основе данных из BreedUpdateDto.
     *
     * @param record объект Breed, который нужно обновить
     * @param dto    объект BreedUpdateDto с обновленными данными
     * @return обновленный объект Breed
     */
    Breed merge(@MappingTarget Breed record, BreedUpdateDto dto);
}
