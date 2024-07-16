package by.vitikova.discovery.converter;

import by.vitikova.discovery.RecordDto;
import by.vitikova.discovery.RecordUpdateDto;
import by.vitikova.discovery.create.RecordCreateDto;
import by.vitikova.discovery.model.entity.Record;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface RecordConverter {

    /**
     * Преобразование объекта Record в объект RecordDto.
     *
     * @param source исходный объект Record
     * @return преобразованный объект RecordDto
     */
    RecordDto convert(Record source);

    /**
     * Преобразование объекта RecordCreateDto в объект Record.
     *
     * @param source исходный объект RecordCreateDto для создания чата
     * @return преобразованный объект Record
     */
    Record convert(RecordCreateDto source);

    /**
     * Преобразование объекта RecordUpdateDto в объект Record.
     *
     * @param source исходный объект RecordUpdateDto
     * @return преобразованный объект Record
     */
    Record convert(RecordUpdateDto source);

    /**
     * Обновление полей объекта Record на основе данных из RecordUpdateDto.
     *
     * @param record объект Record, который нужно обновить
     * @param dto    объект RecordUpdateDto с обновленными данными
     * @return обновленный объект Record
     */
    Record merge(@MappingTarget Record record, RecordUpdateDto dto);
}