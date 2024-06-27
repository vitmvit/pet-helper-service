package by.vitikova.discovery.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Audited
public class NotificationTime {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;
    private LocalDateTime date;

    private Long recordId;
    private Long eventId;
    private Long stateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "notification_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_time_notification_id_to_id")
    )
    private Notification notification;
}
