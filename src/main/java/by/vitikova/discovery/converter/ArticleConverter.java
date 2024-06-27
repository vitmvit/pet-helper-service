package by.vitikova.discovery.converter;

import by.vitikova.discovery.ArticleDto;
import by.vitikova.discovery.EventDto;
import by.vitikova.discovery.create.ArticleCreateDto;
import by.vitikova.discovery.create.EventCreateDto;
import by.vitikova.discovery.model.entity.Article;
import by.vitikova.discovery.model.entity.Event;
import by.vitikova.discovery.update.ArticleUpdateDto;
import by.vitikova.discovery.update.EventUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ArticleConverter {

    /**
     * Преобразование объекта Article в объект ArticleDto.
     *
     * @param source исходный объект Article
     * @return преобразованный объект ArticleDto
     */
    ArticleDto convert(Article source);

    /**
     * Преобразование объекта ArticleCreateDto в объект Article.
     *
     * @param source исходный объект ArticleCreateDto для создания чата
     * @return преобразованный объект Article
     */
    Article convert(ArticleCreateDto source);

    /**
     * Преобразование объекта ArticleUpdateDto в объект Article.
     *
     * @param source исходный объект ArticleUpdateDto
     * @return преобразованный объект Article
     */
    Article convert(ArticleUpdateDto source);

    /**
     * Обновление полей объекта Article на основе данных из ArticleUpdateDto.
     *
     * @param record объект Article, который нужно обновить
     * @param dto    объект ArticleUpdateDto с обновленными данными
     * @return обновленный объект Article
     */
    Article merge(@MappingTarget Article record, ArticleUpdateDto dto);
}
