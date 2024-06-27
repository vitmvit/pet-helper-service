package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.NotificationTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationTimeRepository extends JpaRepository<NotificationTime, Long> {

    List<NotificationTime> findNotificationTimeByNotification_Id(Long id);

    List<NotificationTime> findNotificationTimesByRecordId(Long id);

    List<NotificationTime> findNotificationTimesByStateId(Long id);
    List<NotificationTime> findNotificationTimesByEventId(Long id);
}
