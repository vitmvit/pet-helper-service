package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.PedigreeDto;
import by.vitikova.discovery.converter.PedigreeConverter;
import by.vitikova.discovery.create.PedigreeCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.model.entity.Pedigree;
import by.vitikova.discovery.repository.PedigreeRepository;
import by.vitikova.discovery.repository.RecordRepository;
import by.vitikova.discovery.service.PedigreeService;
import by.vitikova.discovery.update.PedigreeUpdateDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PedigreeServiceImpl implements PedigreeService {

    private static final Logger logger = LoggerFactory.getLogger(PedigreeServiceImpl.class);
    private PedigreeRepository pedigreeRepository;
    private RecordRepository recordRepository;
    private PedigreeConverter pedigreeConverter;

    /**
     * Находит запись родословной по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект PedigreeDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "pedigree", key = "#id")
    @Override
    public PedigreeDto findById(Long id) {
        logger.info("PedigreeService: find pedigree with id: " + id);
        return pedigreeConverter.convert(pedigreeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Находит родоловую по идентификатору записи.
     *
     * @param id Идентификатор записи.
     * @return Список объектов PedigreeDto, представляющих найденные записи.
     */
    @Override
    public PedigreeDto findByRecordId(Long id) {
        logger.info("PedigreeService: find pedigree with record id: " + id);
        var pedigree = pedigreeRepository.findPedigreeByRecordId(id).orElse(new Pedigree());
        return pedigreeConverter.convert(pedigree);
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов PedigreeDto, представляющих все записи.
     */
    @Override
    public List<PedigreeDto> findAll() {
        logger.info("PedigreeService: find all pedigree");
        var listRecord = pedigreeRepository.findAll();
        return listRecord.stream().map(pedigreeConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект PedigreeCreateDto, содержащий данные для создания записи.
     * @return Объект PedigreeDto, представляющий созданную запись.
     */
    @CacheEvict(value = "pedigrees", key = "#dto.recordId")
    @Override
    public PedigreeDto create(PedigreeCreateDto dto) {
        logger.info("PedigreeService: create pedigree");

        if (dto.getRecordId() != null) {
            var record = recordRepository.findById(dto.getRecordId());
            if (record.isPresent()) {
                record.get().setHasPedigree(true);
                recordRepository.save(record.get());
            }
        }

        var pedigree = pedigreeConverter.convert(dto);
        return pedigreeConverter.convert(pedigreeRepository.save(pedigree));
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект PedigreeUpdateDto, содержащий обновленные данные для записи.
     * @return Объект PedigreeDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "pedigrees", key = "#dto.id")
    @Override
    public PedigreeDto update(PedigreeUpdateDto dto) {
        logger.info("PedigreeService: update pedigree");
        var record = pedigreeRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        pedigreeConverter.merge(record, dto);

        return pedigreeConverter.convert(pedigreeRepository.save(record));
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "pedigrees", allEntries = true)
    @Override
    public void delete(Long id) {
        logger.info("PedigreeService: delete pedigree with id: " + id);
        pedigreeRepository.deleteById(id);
    }
}
