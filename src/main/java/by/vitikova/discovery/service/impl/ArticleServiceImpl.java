package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.ArticleDto;
import by.vitikova.discovery.constant.ArticleStatus;
import by.vitikova.discovery.converter.ArticleConverter;
import by.vitikova.discovery.create.ArticleCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.ArticleRepository;
import by.vitikova.discovery.service.ArticleService;
import by.vitikova.discovery.update.ArticleUpdateDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private ArticleRepository articleRepository;
    private ArticleConverter articleConverter;


    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект AnimalTypeDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "article", key = "#id")
    @Override
    public ArticleDto findById(Long id) {
        logger.info("ArticleService: find article type with id: " + id);
        return articleConverter.convert(articleRepository.findById(id).orElseThrow(EntityNotFoundException::new));

    }

    /**
     * Метод для получения списка объектов ArticleDto, соответствующих словарю с заданным идентификатором.
     *
     * @param name идентификатор словаря, для которого необходимо получить список состояний
     * @return список объектов ArticleDto, соответствующих заданному словарю
     */
    @Override
    public List<ArticleDto> findByEditorName(String name) {
        logger.info("ArticleService: find stats with editor name: " + name);
        var articleList = articleRepository.findByRedactorName(name);
        return articleList.stream().map(articleConverter::convert).collect(Collectors.toList());
    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов ArticleDto, представляющих все записи.
     */
    @Override
    public List<ArticleDto> findAll() {
        logger.info("ArticleService: find all articles");
        var articleList = articleRepository.findAll();
        return articleList.stream().map(articleConverter::convert).collect(Collectors.toList());

    }

    @Override
    public List<ArticleDto> findAllByStatus(ArticleStatus status) {
        var articleList = articleRepository.findAllByStatus(status);
        return articleList.stream().map(articleConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект ArticleCreateDto, содержащий данные для создания записи.
     * @return Объект ArticleDto, представляющий созданную запись.
     */
    @CacheEvict(value = "articles", key = "#dto.name")
    @Transactional
    @Override
    public ArticleDto create(ArticleCreateDto dto) {
        logger.info("ArticleService: create article");
        var article = articleConverter.convert(dto);
        return articleConverter.convert(articleRepository.save(article));
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект ArticleUpdateDto, содержащий обновленные данные для записи.
     * @return Объект ArticleDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "articles", key = "#dto.id")
    @Transactional
    @Override
    public ArticleDto update(ArticleUpdateDto dto) {
        logger.info("ArticleService: update article with id: " + dto.getId());
        var article = articleRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        articleConverter.merge(article, dto);
        return articleConverter.convert(articleRepository.save(article));
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "articles", allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("ArticleService: delete article with id: " + id);
        articleRepository.deleteById(id);
    }
}
