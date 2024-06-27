package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.TagDto;
import by.vitikova.discovery.converter.TagConverter;
import by.vitikova.discovery.create.TagCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.model.entity.Tag;
import by.vitikova.discovery.repository.ArticleRepository;
import by.vitikova.discovery.repository.TagRepository;
import by.vitikova.discovery.service.TagService;
import by.vitikova.discovery.update.TagUpdateDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);
    private TagRepository tagRepository;
    private ArticleRepository articleRepository;
    private TagConverter tagConverter;

    /**
     * Находит объект TagDto по его идентификатору.
     *
     * @param id идентификатор объекта Tag
     * @return объект TagDto, соответствующий найденному id
     * @throws EntityNotFoundException если объект TagDto не найден
     */
    @Cacheable(value = "tag", key = "#id")
    @Override
    public TagDto findById(Long id) {
        logger.info("TagService: find tag with id: " + id);
        return tagConverter.convert(tagRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<TagDto> findByArticleId(Long id) {
        logger.info("TagService: find tags with article id: " + id);
        var tagList = tagRepository.findByArticle_Id(id);
        return tagList.stream().map(tagConverter::convert).collect(Collectors.toList());

    }

    @Override
    public TagDto findByName(String name) {
        logger.info("TagService: find tag with name: " + name);
        var tag = tagRepository.findByName(name).orElse(null);
        return tagConverter.convert(tag);
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов TagDto, представляющих все записи.
     */
    @Override
    public List<TagDto> findAll() {
        logger.info("TagService: find all Tags");
        var tagList = tagRepository.findAll();
        return tagList.stream().map(tagConverter::convert).collect(Collectors.toList());

    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект TagCreateDto, содержащий данные для создания записи.
     * @return Объект TagDto, представляющий созданную запись.
     */
    @CacheEvict(value = "tags", key = "#dto.name")
    @Transactional
    @Override
    public TagDto create(TagCreateDto dto) {
        logger.info("TagService: create tag");
        var tag = tagConverter.convert(dto);
        return tagConverter.convert(tagRepository.save(tag));
    }

    @Override
    public List<TagDto> createAll(List<TagCreateDto> dtoList) {
        var article = articleRepository.findById(dtoList.get(0).getArticleId()).orElseThrow(EntityNotFoundException::new);
        List<TagDto> tagDtoList = new ArrayList<>();
        for (TagCreateDto item : dtoList) {
            var a = tagConverter.convert(item);
            a.setArticle(article);
            var b = tagRepository.save(a);
            var c = tagConverter.convert(b);
            tagDtoList.add(c);
        }
        return tagDtoList;
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект TagUpdateDto, содержащий обновленные данные для записи.
     * @return Объект TagDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "tags", key = "#dto.id")
    @Transactional
    @Override
    public TagDto update(TagUpdateDto dto) {
        logger.info("TagService: update tag with id: " + dto.getId());
        var tag = tagRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        tagConverter.merge(tag, dto);
        return tagConverter.convert(tagRepository.save(tag));
    }

    public void deleteByArticleId(Long id) {
        var list = tagRepository.findByArticle_Id(id);
        for (Tag item : list) {
            tagRepository.deleteById(item.getId());
        }
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "tags", allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("TagService: delete tag with id: " + id);
        tagRepository.deleteById(id);
    }
}
