package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.FavoriteArticleDto;
import by.vitikova.discovery.converter.FavoriteArticleConverter;
import by.vitikova.discovery.create.FavoriteArticleCreateDto;
import by.vitikova.discovery.exception.EntityNotFoundException;
import by.vitikova.discovery.repository.FavoriteArticleRepository;
import by.vitikova.discovery.service.FavoriteArticleService;
import by.vitikova.discovery.update.FavoriteArticleUpdateDto;
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
public class FavoriteArticleServiceImpl implements FavoriteArticleService {

    private static final Logger logger = LoggerFactory.getLogger(FavoriteArticleServiceImpl.class);
    private FavoriteArticleRepository favoriteArticleRepository;
    private FavoriteArticleConverter favoriteArticleConverter;

    /**
     * Находит запись по идентификатору.
     *
     * @param id Идентификатор записи.
     * @return Объект FavoriteArticleDto, представляющий найденную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @Cacheable(value = "favorite", key = "#id")
    @Override
    public FavoriteArticleDto findById(Long id) {
        logger.info("FavoriteArticleService: find favorite article with id: " + id);
        return favoriteArticleConverter.convert(favoriteArticleRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Возвращает избранную статью по идентификатору статьи.
     *
     * @param id идентификатор статьи
     * @return избранная статья
     */
    @Override
    public FavoriteArticleDto findByArticleId(Long id) {
        logger.info("FavoriteArticleService: find favorite article with id: " + id);
        return favoriteArticleConverter.convert(favoriteArticleRepository.findByArticleId(id).orElseThrow(EntityNotFoundException::new));

    }

    /**
     * Возвращает список избранных статей для заданного пользователя.
     *
     * @param name имя пользователя
     * @return список избранных статей
     */
    @Override
    public List<FavoriteArticleDto> findByUserLogin(String name) {
        logger.info("FavoriteArticleService: find favorite articles with user name: " + name);
        var favoriteArticleList = favoriteArticleRepository.findByUserLogin(name);
        return favoriteArticleList.stream().map(favoriteArticleConverter::convert).collect(Collectors.toList());

    }

    /**
     * Возвращает все записи.
     *
     * @return Список объектов FavoriteArticleDto, представляющих все записи.
     */
    @Override
    public List<FavoriteArticleDto> findAll() {
        logger.info("FavoriteArticleService: find all favorite articles");
        var favoriteArticleList = favoriteArticleRepository.findAll();
        return favoriteArticleList.stream().map(favoriteArticleConverter::convert).collect(Collectors.toList());
    }

    /**
     * Создает новую запись.
     *
     * @param dto Объект FavoriteArticleCreateDto, содержащий данные для создания записи.
     * @return Объект FavoriteArticleDto, представляющий созданную запись.
     */
    @CacheEvict(value = "favorites", key = "#dto.articleId")
    @Transactional
    @Override
    public FavoriteArticleDto create(FavoriteArticleCreateDto dto) {
        logger.info("FavoriteArticleService: create favorite article");
        var favoriteArticle = favoriteArticleConverter.convert(dto);
        return favoriteArticleConverter.convert(favoriteArticleRepository.save(favoriteArticle));
    }

    /**
     * Обновляет данные записи.
     *
     * @param dto Объект ArticleUpdateDto, содержащий обновленные данные для записи.
     * @return Объект ArticleDto, представляющий обновленную запись.
     * @throws EntityNotFoundException если запись не найдена.
     */
    @CacheEvict(value = "favorites", key = "#dto.id")
    @Transactional
    @Override
    public FavoriteArticleDto update(FavoriteArticleUpdateDto dto) {
        logger.info("FavoriteArticleService: update favorite article with id: " + dto.getId());
        var article = favoriteArticleRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        favoriteArticleConverter.merge(article, dto);
        return favoriteArticleConverter.convert(favoriteArticleRepository.save(article));
    }

    /**
     * Удаляет запись по идентификатору.
     *
     * @param id Идентификатор записи для удаления.
     */
    @CacheEvict(value = "favorites", allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("FavoriteArticleService: delete article with id: " + id);
        favoriteArticleRepository.deleteFavoriteArticleById(id);
    }
}
