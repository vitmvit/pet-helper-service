package by.vitikova.discovery.controller;

import by.vitikova.discovery.NotificationTimeDto;
import by.vitikova.discovery.create.NotificationTimeCreateDto;
import by.vitikova.discovery.service.NotificationTimeService;
import by.vitikova.discovery.update.NotificationTimeUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/notificationTimes")
public class NotificationTimeController {

    private NotificationTimeService notificationTimeService;

    @GetMapping("/{id}")
    public ResponseEntity<NotificationTimeDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationTimeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<NotificationTimeDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationTimeService.findAll());
    }

    @GetMapping("dict/{id}")
    public ResponseEntity<List<NotificationTimeDto>> findByNotificationId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationTimeService.findByNotificationId(id));
    }

    @PostMapping
    public ResponseEntity<NotificationTimeDto> create(@RequestBody NotificationTimeCreateDto notificationTimeCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notificationTimeService.create(notificationTimeCreateDto));
    }

    @PutMapping
    public ResponseEntity<NotificationTimeDto> update(@RequestBody NotificationTimeUpdateDto notificationTimeUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationTimeService.update(notificationTimeUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        notificationTimeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
