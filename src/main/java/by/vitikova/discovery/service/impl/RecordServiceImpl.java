package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.RecordDto;
import by.vitikova.discovery.RecordUpdateDto;
import by.vitikova.discovery.converter.RecordConverter;
import by.vitikova.discovery.create.RecordCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.feign.ImageClient;
import by.vitikova.discovery.model.entity.Notification;
import by.vitikova.discovery.model.entity.NotificationTime;
import by.vitikova.discovery.model.entity.Record;
import by.vitikova.discovery.repository.*;
import by.vitikova.discovery.service.RecordService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для работы с записями.
 */
@Service
@AllArgsConstructor
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;
    private PedigreeRepository pedigreeRepository;
    private EventDictionaryRepository eventDictionaryRepository;
    private NotificationTimeRepository notificationTimeRepository;
    private NotificationRepository notificationRepository;
    private StateDictionaryRepository stateDictionaryRepository;
    private RecordConverter recordConverter;
    private ImageClient imageClient;
    private static final Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class);

    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект RecordDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "record", key = "#id")
    @Override
    public RecordDto findById(Long id) {
        logger.info("RecordService: find record with id: " + id);
        return recordConverter.convert(recordRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Находит записи по идентификатору пользователя.
     *
     * @param login Идентификатор пользователя.
     * @return Список объектов RecordDto, представляющих найденные записи.
     */
    @Override
    public List<RecordDto> findByUserLogin(String login) {
        logger.info("RecordService: find record with user login: " + login);
        var listRecord = recordRepository.findRecordByUserLogin(login);
        return listRecord.stream().map(recordConverter::convert).collect(Collectors.toList());
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов RecordDto, представляющих все записи.
     */
    @Override
    public List<RecordDto> findAll() {
        logger.info("RecordService: find all record");
        var listRecord = recordRepository.findAll();
        return listRecord.stream().map(recordConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект RecordCreateDto, содержащий данные для создания записи.
     * @return Объект RecordDto, представляющий созданную запись.
     */
    @Transactional
    @Override
    public RecordDto create(RecordCreateDto dto) {
        logger.info("RecordService: create record");
        var record = recordConverter.convert(dto);
//        if (record.getUuidAvatar().equals("")) {
//            record.setUuidAvatar(DEFAULT_UUID);
//        }
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
    @CacheEvict(value = "records", key = "#recordId")
    @Transactional
    @Override
    public RecordDto updateAvatarUuid(Long recordId, String uuidAvatar) {
        logger.info("RecordService: update avatar for record");
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
    @CacheEvict(value = "records", key = "#dto.id")
    @Transactional
    @Override
    public RecordDto update(RecordUpdateDto dto) {
        logger.info("RecordService: update record");
        var record = recordRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        recordConverter.merge(record, dto);
        return recordConverter.convert(recordRepository.save(record));
    }

    @Override
    public void deleteRecordsByUserLogin(String login) {
        var recordList = recordRepository.findRecordByUserLogin(login);
        for (Record item : recordList) {
            delete(item.getId());
        }
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "records", allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {
        var record = recordRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        pedigreeRepository.findPedigreeByRecordId(id).ifPresent(pedigree -> pedigreeRepository.delete(pedigree));

        var eventDictionaries = eventDictionaryRepository.findAllByRecordId(id);
        eventDictionaryRepository.deleteAll(eventDictionaries);

        var stateDictionaries = stateDictionaryRepository.findAllByRecordId(id);
        stateDictionaryRepository.deleteAll(stateDictionaries);

        var notificationTimes = notificationTimeRepository.findNotificationTimesByRecordId(id);
        var setId = notificationTimes.stream()
                .map(NotificationTime::getNotification)
                .map(Notification::getId)
                .collect(Collectors.toSet());
        notificationRepository.deleteAllById(setId);

        if (record.getUuidAvatar() != null && !Objects.equals(record.getUuidAvatar(), "")) {
            imageClient.removeAvatar(record.getUuidAvatar());
        }

        logger.info("RecordService: delete record with id: " + id);
        recordRepository.deleteById(id);
    }
}