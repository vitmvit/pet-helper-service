package by.vitikova.discovery.service;

import by.vitikova.discovery.RecordDto;
import by.vitikova.discovery.RecordUpdateDto;
import by.vitikova.discovery.create.RecordCreateDto;

import java.util.List;

public interface RecordService {

    RecordDto findById(Long id);

    List<RecordDto> findByUserLogin(String login);

    List<RecordDto> findAll();

    RecordDto create(RecordCreateDto dto);

    RecordDto updateAvatarUuid(Long recordId, String uuidAvatar);

    RecordDto update(RecordUpdateDto dto);

    void deleteRecordsByUserLogin(String login);

    void delete(Long id);
}