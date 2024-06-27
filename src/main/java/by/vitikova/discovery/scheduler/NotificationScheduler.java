//package by.vitikova.discovery.scheduler;
//
//import by.vitikova.discovery.NotificationTimeDto;
//import by.vitikova.discovery.model.entity.Notification;
//import by.vitikova.discovery.repository.NotificationRepository;
//import by.vitikova.discovery.service.NotificationTimeService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class NotificationScheduler {
//
//    private final NotificationTimeService notificationTimeService;
//    private final NotificationRepository notificationRepository;
//    private static final Logger log = LoggerFactory.getLogger(NotificationScheduler.class);
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//
//    @Autowired
//    public NotificationScheduler(NotificationTimeService eventService, NotificationRepository notificationRepository) {
//        this.notificationTimeService = eventService;
//        this.notificationRepository = notificationRepository;
//    }
//
//    @Scheduled(cron = "* * * * * *") // Запуск шедулера каждую секунду
//    public void scheduleTask() {
//        log.info("NotificationScheduler {}", dateFormat.format(new Date()));
//        LocalDateTime now = LocalDateTime.now();
//
//        List<Notification> activeNotifications = notificationRepository.findNotificationsByIsActiveTrue();
//
//        for (Notification notification : activeNotifications) {
//            List<NotificationTimeDto> notificationTimes = notificationTimeService.findByNotificationId(notification.getId());
//            var newList = notificationTimes.stream().filter(x -> (x.getDate().toLocalDate().isEqual(now.toLocalDate()) && x.getTime().toLocalTime().equals(now.toLocalTime()))).toList();
//            if (newList.size() == 0) {
//                notification.setActive(false);
//                notificationRepository.save(notification);
//            }
//        }
//    }
//}
