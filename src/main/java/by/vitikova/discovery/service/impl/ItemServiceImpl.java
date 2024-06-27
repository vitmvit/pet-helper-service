//package by.vitikova.discovery.service.impl;
//
//import by.vitikova.discovery.ItemDto;
//import by.vitikova.discovery.converter.ItemConverter;
//import by.vitikova.discovery.create.ItemCreateDto;
//import by.vitikova.discovery.exception.EntityNotFoundException;
//import by.vitikova.discovery.repository.ItemRepository;
//import by.vitikova.discovery.service.ItemService;
//import by.vitikova.discovery.update.ItemUpdateDto;
//import lombok.AllArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class ItemServiceImpl implements ItemService {
//
//    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
//    private ItemRepository itemRepository;
//    private ItemConverter itemConverter;
//
//    /**
//     * Находит объект ItemDto по его идентификатору.
//     *
//     * @param id идентификатор объекта NotExistParent
//     * @return объект ItemDto, соответствующий найденному id
//     * @throws EntityNotFoundException если объект ItemDto не найден
//     */
//    @Cacheable(value = "item", key = "#id")
//    @Override
//    public ItemDto findById(Long id) {
//        logger.info("ItemService: find item with id: " + id);
//        return itemConverter.convert(itemRepository.findById(id).orElseThrow(EntityNotFoundException::new));
//
//    }
//
//    @Override
//    public List<ItemDto> findByArticleId(Long id) {
//        logger.info("ItemService: find stats with dictionary id: " + id);
//        var itemList = itemRepository.findByArticle_Id(id);
//        return itemList.stream().map(itemConverter::convert).collect(Collectors.toList());
//
//    }
//
//    /**
//     * Возвращает все записи.
//     *
//     * @return Список объектов ItemDto, представляющих все записи.
//     */
//    @Override
//    public List<ItemDto> findAll() {
//        logger.info("ItemService: find all items");
//        var itemList = itemRepository.findAll();
//        return itemList.stream().map(itemConverter::convert).collect(Collectors.toList());
//
//    }
//
//    /**
//     * Создает новую запись.
//     *
//     * @param dto Объект ItemCreateDto, содержащий данные для создания записи.
//     * @return Объект ItemDto, представляющий созданную запись.
//     */
//    @CacheEvict(value = "items", key = "#dto.text")
//    @Transactional
//    @Override
//    public ItemDto create(ItemCreateDto dto) {
//        logger.info("ItemService: create item");
//        var item = itemConverter.convert(dto);
//        return itemConverter.convert(itemRepository.save(item));
//    }
//
//    /**
//     * Обновляет данные записи.
//     *
//     * @param dto Объект ItemUpdateDto, содержащий обновленные данные для записи.
//     * @return Объект ItemDto, представляющий обновленную запись.
//     * @throws EntityNotFoundException если запись не найдена.
//     */
//    @CacheEvict(value = "items", key = "#dto.id")
//    @Transactional
//    @Override
//    public ItemDto update(ItemUpdateDto dto) {
//        logger.info("ItemService: update item with id: " + dto.getId());
//        var item = itemRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
//        itemConverter.merge(item, dto);
//        return itemConverter.convert(itemRepository.save(item));
//    }
//
//    /**
//     * Удаляет запись по идентификатору.
//     *
//     * @param id Идентификатор записи для удаления.
//     */
//    @CacheEvict(value = "items", allEntries = true)
//    @Transactional
//    @Override
//    public void delete(Long id) {
//        logger.info("ItemService: delete item with id: " + id);
//        itemRepository.deleteById(id);
//    }
//}
