//package by.vitikova.discovery.converter;
//
//import by.vitikova.discovery.ItemDto;
//import by.vitikova.discovery.create.ItemCreateDto;
//import by.vitikova.discovery.model.entity.Item;
//import by.vitikova.discovery.update.ItemUpdateDto;
//import org.mapstruct.Mapper;
//import org.mapstruct.MappingTarget;
//import org.mapstruct.ReportingPolicy;
//
//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
//
//public interface ItemConverter {
//
//    /**
//     * Преобразование объекта Item в объект ItemDto.
//     *
//     * @param source исходный объект Item
//     * @return преобразованный объект ItemDto
//     */
//    ItemDto convert(Item source);
//
//    /**
//     * Преобразование объекта ItemCreateDto в объект Item.
//     *
//     * @param source исходный объект ItemCreateDto для создания чата
//     * @return преобразованный объект Item
//     */
//    Item convert(ItemCreateDto source);
//
//    /**
//     * Преобразование объекта ItemUpdateDto в объект Item.
//     *
//     * @param source исходный объект ItemUpdateDto
//     * @return преобразованный объект Item
//     */
//    Item convert(ItemUpdateDto source);
//
//    /**
//     * Обновление полей объекта Item на основе данных из ItemUpdateDto.
//     *
//     * @param item объект Item, который нужно обновить
//     * @param dto  объект ItemUpdateDto с обновленными данными
//     * @return обновленный объект Item
//     */
//    Item merge(@MappingTarget Item item, ItemUpdateDto dto);
//}
