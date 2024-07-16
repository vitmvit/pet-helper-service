package by.vitikova.discovery.controller;

import by.vitikova.discovery.NotificationDto;
import by.vitikova.discovery.create.NotificationCreateDto;
import by.vitikova.discovery.service.NotificationService;
import by.vitikova.discovery.update.NotificationUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private NotificationService notificationTimeService;

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationTimeService.findById(id));
    }

    @GetMapping("/userLogin/{login}")
    public ResponseEntity<List<NotificationDto>> findByUserLogin(@PathVariable("login") String login) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationTimeService.findByUserLogin(login));
    }

    @GetMapping
    public ResponseEntity<List<NotificationDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationTimeService.findAll());
    }

    @PostMapping
    public ResponseEntity<NotificationDto> create(@RequestBody NotificationCreateDto notificationCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notificationTimeService.create(notificationCreateDto));
    }

    @PutMapping
    public ResponseEntity<NotificationDto> update(@RequestBody NotificationUpdateDto notificationUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationTimeService.update(notificationUpdateDto));
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<NotificationDto> updateStatus(@PathVariable("id") Long id, @PathVariable("status") boolean status) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationTimeService.updateStatus(id, status));
    }

    @DeleteMapping("/delete/user/{login}")
    public ResponseEntity<Void> deleteNotificationsByUserLogin(@PathVariable("login") String login) {
        notificationTimeService.deleteByUserLogin(login);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        notificationTimeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
