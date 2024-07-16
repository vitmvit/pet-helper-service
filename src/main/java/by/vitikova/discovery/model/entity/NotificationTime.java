package by.vitikova.discovery.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTime {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;
    private LocalDateTime date;

    private Long recordId;
    private Long eventId;
    private Long stateId;

    @ManyToOne
    @JoinColumn(
            name = "notification_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_time_notification_id_to_id")
    )
    private Notification notification;
}
