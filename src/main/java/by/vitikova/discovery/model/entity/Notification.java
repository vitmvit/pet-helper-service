package by.vitikova.discovery.model.entity;

import by.vitikova.discovery.listener.NotificationListener;
import by.vitikova.discovery.model.entity.parent.LogModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(NotificationListener.class)
public class Notification extends LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userLogin;
    private String name;
    private String description;
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notification", cascade = CascadeType.ALL)
    private List<NotificationTime> listNotificationTime;
}
