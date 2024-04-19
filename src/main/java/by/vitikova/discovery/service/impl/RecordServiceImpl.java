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

@Service
@AllArgsConstructor
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;
    private RecordConverter recordConverter;

    @Override
    public RecordDto findById(Long id) {
        return recordConverter.convert(recordRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<RecordDto> findByUserId(String id) {
        var listRecord = recordRepository.findRecordByUserLogin(id);
        return listRecord.stream().map(recordConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<RecordDto> findAll() {
        var listRecord = recordRepository.findAll();
        return listRecord.stream().map(recordConverter::convert).collect(Collectors.toList());
    }

    @Override
    public RecordDto create(RecordCreateDto dto) {
        var record = recordConverter.convert(dto);
        if (record.getUuidAvatar().equals("")) {
            record.setUuidAvatar(DEFAULT_UUID);
        }
        return recordConverter.convert(recordRepository.save(record));
    }

    @Override
    public RecordDto updateAvatarUuid(Long recordId, String uuidAvatar) {
        var record = recordRepository.findById(recordId).orElseThrow(EntityNotFoundException::new);
        record.setUuidAvatar(uuidAvatar);
        return recordConverter.convert(recordRepository.save(record));
    }

    @Override
    public RecordDto update(RecordUpdateDto dto) {
        var record = recordRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        recordConverter.merge(record, dto);
        return recordConverter.convert(recordRepository.save(record));
    }

    @Override
    public void delete(Long id) {
        recordRepository.deleteById(id);
    }
}