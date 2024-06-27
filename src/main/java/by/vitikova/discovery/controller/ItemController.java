//package by.vitikova.discovery.controller;
//
//import by.vitikova.discovery.ItemDto;
//import by.vitikova.discovery.create.ItemCreateDto;
//import by.vitikova.discovery.service.ItemService;
//import by.vitikova.discovery.update.ItemUpdateDto;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/v1/items")
//public class ItemController {
//
//    private ItemService itemService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ItemDto> findById(@PathVariable("id") Long id) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(itemService.findById(id));
//    }
//
//    @GetMapping("/article/{id}")
//    public ResponseEntity<List<ItemDto>> findByArticleId(@PathVariable("id") Long id) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(itemService.findByArticleId(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ItemDto>> findAll() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(itemService.findAll());
//    }
//
//    @PostMapping
//    public ResponseEntity<ItemDto> create(@RequestBody ItemCreateDto dto) {
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(itemService.create(dto));
//    }
//
//    @PutMapping
//    public ResponseEntity<ItemDto> update(@RequestBody ItemUpdateDto dto) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(itemService.update(dto));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
//        itemService.delete(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
//}
