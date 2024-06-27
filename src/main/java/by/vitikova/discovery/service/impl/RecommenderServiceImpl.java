package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.*;
import by.vitikova.discovery.constant.ArticleStatus;
import by.vitikova.discovery.service.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static by.vitikova.discovery.constant.Constant.GENERAL_TAG;
import static by.vitikova.discovery.constant.Constant.HAS_EXHIBITION;

@Service
@AllArgsConstructor
public class RecommenderServiceImpl implements RecommenderService {

    private static final Logger logger = LoggerFactory.getLogger(RecommenderServiceImpl.class);

    private RecordService recordRepository;
    private StateDictionaryService stateDictionaryRepository;
    private ArticleService articleService;
    private TagService tagService;
    private EventDictionaryService eventDictionaryRepository;

    private Set<String> getTags(String userLogin) {
        Set<String> listTags = new HashSet<>();
        var recordList = recordRepository.findByUserLogin(userLogin);
        for (RecordDto recordItem : recordList) {
            listTags.add(recordItem.getName().toLowerCase());
            if (recordItem.getAnimalType() != null) {
                listTags.add(recordItem.getAnimalType().toLowerCase());
            }
            if (recordItem.getBreed() != null) {
                listTags.add(recordItem.getBreed().toLowerCase());
            }
            if (recordItem.getSex() != null) {
                listTags.add(recordItem.getSex().getSex().toLowerCase());
            }
            if (recordItem.isHasExhibition()) {
                listTags.add(HAS_EXHIBITION.toLowerCase());
            }

            var stateList = stateDictionaryRepository.findAllByRecordId(recordItem.getId());
            for (StateDictionaryDto stateItem : stateList) {
                listTags.add(stateItem.getName());
            }
            var eventList = eventDictionaryRepository.findAllByRecordId(recordItem.getId());
            for (EventDictionaryDto eventItem : eventList) {
                listTags.add(eventItem.getName());
                if (eventItem.getTypeVaccination() != null) {
                    listTags.add(eventItem.getTypeVaccination());
                }
            }
        }
        logger.info(listTags.toString());
        return listTags;
    }

    public List<ArticleDto> getArticle(String userLogin) {
        var articleList = articleService.findAllByStatus(ArticleStatus.FINISHED);
        Set<String> tagSet = getTags(userLogin);
        tagSet.add(GENERAL_TAG);

        // Оценить релевантность статей
        Map<ArticleDto, Integer> articleRelevanceMap = new HashMap<>();
        for (ArticleDto article : articleList) {
            Set<String> articleTags = tagService.findByArticleId(article.getId()).stream().map(TagDto::getName).collect(Collectors.toSet());
            articleTags.add(GENERAL_TAG);
            int relevanceScore = calculateRelevanceScore(tagSet, articleTags);
            articleRelevanceMap.put(article, relevanceScore);
        }

        // Отсортировать статьи по релевантности
        List<ArticleDto> sortedArticles = articleRelevanceMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .toList();

        return sortedArticles.stream().limit(7).collect(Collectors.toList());
    }

    private int calculateRelevanceScore(Set<String> userTags, Set<String> articleTags) {
        int intersectionSize = userTags.stream()
                .filter(articleTags::contains)
                .toList()
                .size();
        return (int) (((double) intersectionSize / (userTags.size() + articleTags.size())) * 100);
    }
}
