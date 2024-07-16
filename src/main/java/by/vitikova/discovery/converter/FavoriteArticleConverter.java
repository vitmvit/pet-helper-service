package by.vitikova.discovery.converter;

import by.vitikova.discovery.FavoriteArticleDto;
import by.vitikova.discovery.create.FavoriteArticleCreateDto;
import by.vitikova.discovery.model.entity.FavoriteArticle;
import by.vitikova.discovery.update.FavoriteArticleUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface FavoriteArticleConverter {

    /**
     * Преобразование объекта FavoriteArticle в объект FavoriteArticleDto.
     *
     * @param source исходный объект FavoriteArticle
     * @return преобразованный объект FavoriteArticleDto
     */
    FavoriteArticleDto convert(FavoriteArticle source);

    /**
     * Преобразование объекта FavoriteArticleCreateDto в объект FavoriteArticle.
     *
     * @param source исходный объект FavoriteArticleCreateDto для создания чата
     * @return преобразованный объект FavoriteArticle
     */
    FavoriteArticle convert(FavoriteArticleCreateDto source);

    /**
     * Преобразование объекта FavoriteArticleUpdateDto в объект FavoriteArticle.
     *
     * @param source исходный объект FavoriteArticleUpdateDto
     * @return преобразованный объект FavoriteArticle
     */
    FavoriteArticle convert(FavoriteArticleUpdateDto source);

    /**
     * Обновление полей объекта FavoriteArticle на основе данных из FavoriteArticleUpdateDto.
     *
     * @param favoriteArticle объект FavoriteArticle, который нужно обновить
     * @param dto             объект FavoriteArticleUpdateDto с обновленными данными
     * @return обновленный объект FavoriteArticle
     */
    FavoriteArticle merge(@MappingTarget FavoriteArticle favoriteArticle, FavoriteArticleUpdateDto dto);
}