package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.RecordDto;
import by.vitikova.discovery.RecordUpdateDto;
import by.vitikova.discovery.converter.RecordConverter;
import by.vitikova.discovery.create.RecordCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.RecordRepository;
import by.vitikova.discovery.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static by.vitikova.discovery.constant.Constant.DEFAULT_UUID;

/**
 * Реализация сервиса для работы с записями.
 */
@Service
@AllArgsConstructor
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;
    private RecordConverter recordConverter;

    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект RecordDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Override
    public RecordDto findById(Long id) {
        return recordConverter.convert(recordRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Находит записи по идентификатору пользователя.
     *
     * @param id Идентификатор пользователя.
     * @return Список объектов RecordDto, представляющих найденные записи.
     */
    @Override
    public List<RecordDto> findByUserId(String id) {
        var listRecord = recordRepository.findRecordByUserLogin(id);
        return listRecord.stream().map(recordConverter::convert).collect(Collectors.toList());
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов RecordDto, представляющих все записи.
     */
    @Override
    public List<RecordDto> findAll() {
        var listRecord = recordRepository.findAll();
        return listRecord.stream().map(recordConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект RecordCreateDto, содержащий данные для создания записи.
     * @return Объект RecordDto, представляющий созданную запись.
     */
    @Override
    public RecordDto create(RecordCreateDto dto) {
        var record = recordConverter.convert(dto);
        if (record.getUuidAvatar().equals("")) {
            record.setUuidAvatar(DEFAULT_UUID);
        }
        return recordConverter.convert(recordRepository.save(record));
    }

    /**
     * Обновляет идентификатор аватара записи.
     *
     * @param recordId   Идентификатор записи.
     * @param uuidAvatar Новый идентификатор аватара.
     * @return Объект RecordDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Override
    public RecordDto updateAvatarUuid(Long recordId, String uuidAvatar) {
        var record = recordRepository.findById(recordId).orElseThrow(EntityNotFoundException::new);
        record.setUuidAvatar(uuidAvatar);
        return recordConverter.convert(recordRepository.save(record));
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект RecordUpdateDto, содержащий обновленные данные для записи.
     * @return Объект RecordDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Override
    public RecordDto update(RecordUpdateDto dto) {
        var record = recordRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        recordConverter.merge(record, dto);
        return recordConverter.convert(recordRepository.save(record));
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @Override
    public void delete(Long id) {
        recordRepository.deleteById(id);
    }
}